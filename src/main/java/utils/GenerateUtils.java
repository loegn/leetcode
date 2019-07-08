package utils;

import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @date : 2019/04/24 15:26
 * @author: liangenmao
 */
public class GenerateUtils {
    private static final String SOURCE_PREFIX = "./src/main/resources/template";
    private static final String TARGET_PREFIX = "./src/main/java";
    private static String[] numbers = {"one", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static String[] upperNumbers = new String[numbers.length];

    static {
        for (int i = 0; i < numbers.length; i++) {
            upperNumbers[i] = numbers[i].substring(0,1).toUpperCase() + numbers[i].substring(1);
        }
    }

    public static class Param {
        private String type;
        private String name;
        private String value;
        private String extra;

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        public String getExtra() {
            return extra;
        }
    }

    public static void generateQuestion(String title, String method) {
        String[] strings = title.split("\\.");
        int num = Integer.parseInt(strings[0]);
        String className = toUp(strings[1]) + strings[0];
        String fileName = "/" + className + ".java";
        String packageName = getPackageName(num);
        String targetPath = TARGET_PREFIX + "/" + packageName + fileName;
        Map<String, Object> params = new HashMap<>();
        params.put("className", className);
        params.put("packageName", packageName);
        params.put("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        params.put("author", "liangenmao");
        Iterator<Map.Entry<String, Object>> iterator = transferMethod(method).entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            params.put(entry.getKey(), entry.getValue());
        }
        generateClass(SOURCE_PREFIX, "/generateQuestion.ftl", targetPath, params);
    }

    private static Map<String, Object> transferMethod(String method) {
        Map<String, Object> map = new HashMap<>();
        method = method.substring(0, method.lastIndexOf(')')).trim();
        int index = method.indexOf('(');
        String[] strings = method.substring(0, index).trim().split(" ");
        map.put("returnName", strings[strings.length - 2]);
        map.put("returnValue", getValueForReturn(map.get("returnName").toString()));
        map.put("methodName", strings[strings.length - 1]);
        String[] params = method.substring(index + 1).split(",");
        List<Param> paramsList = new ArrayList();
        boolean flag = false;
        for (int i = 0; i < params.length; i++) {
            Param param = getParam(params[i]);
            paramsList.add(param);
            if (!flag && param.extra != null) {
                flag = true;
            }
        }
        map.put("params", paramsList);
        addImport(map, flag);
        return map;
    }

    /**
     * 添加额外的包
     */
    private static void addImport(Map<String, Object> map, boolean flag) {
        List<String> packageList = new ArrayList<>();
        HashSet<String> paramType = new HashSet<>();
        paramType.add(map.get("returnName").toString());
        List<Param> paramsList = (List<Param>) map.get("params");
        for (Param param : paramsList) {
            paramType.add(param.name);
        }
        File file = new File("./src/main/java/pojo");
        Set<String> fileSet = new HashSet<>();
        for (File listFile : file.listFiles()) {
            fileSet.add(listFile.getName().split("\\.")[0]);
        }
        Iterator<String> iterator = paramType.iterator();
        while (iterator.hasNext()) {
            String type = iterator.next();
            if (fileSet.contains(type)) {
                packageList.add("pojo." + type);
            } else if (type.startsWith("List")) {
                packageList.add("java.util.List");
            } else if (type.startsWith("Map")) {
                packageList.add("java.util.Map");
            }
        }
        if (flag) {
            packageList.add("utils.ParamUtils");
        }
        map.put("imports", packageList);
    }

    private static Param getParam(String str) {
        String[] strings = str.trim().split(" ");
        Param param = new Param();
        param.type = strings[0];
        param.name = strings[1];
        param.value = getValue(param.type);
        param.extra = getExtra(param.type);
        return param;
    }

    private static String getValue(String type) {
        switch (type) {
            case "int":
                return "0";
            case "boolean":
                return "false";
            case "int[][]":
                return "ParamUtils.getInts(ints)";
            case "char[][]":
                return "ParamUtils.getChars(chars)";
            case "int[]":
                return "ParamUtils.getInt(ints)";
            case "Interval":
                return "ParamUtils.getInterval(interval)";
            case "ListNode":
                return "ParamUtils.getListNode(listNode)";
            default:
                return "null";
        }
    }

    private static String getValueForReturn(String type) {
//        String value;
//        switch (type) {
//            case "ListNode":
//                value = "0";
//                break;
//            default:
//                value = "";
//        }
        String result = getValue(type);
        if (result.startsWith("ParamUtils.")) {
            return "null";
        } else {
            return result;
        }
    }

    private static String getExtra(String type) {
        switch (type) {
            case "int[][]":
                return "String ints = \"\";";
            case "char[][]":
                return "String chars = \"\";";
            case "int[]":
                return "String ints = \"\";";
            case "Interval":
                return "String interval  = \"\";";
            case "ListNode":
                return "String listNode = \"\";";
            default:
                return null;
        }
    }

    private static String getMethodName(String method) {
        method = method.trim();
        method = method.substring(0, method.indexOf('(')).trim();
        return method.substring(method.lastIndexOf(' ')).trim();
    }

    private static String getPackageName(int num) {
        StringBuilder sb = new StringBuilder();
        if (num > 999) {
            sb.append("OneThousandAnd");
            num -= 1000;
        }
        num /= 100;
        String hundred = "Hundred";
        String result = upperNumbers[num] + (num == 0 ? "" : hundred) + "To" + (num + 1 <= 1 ? "" : (upperNumbers[num + 1].substring(0, 1).toUpperCase() + upperNumbers[num + 1].substring(1))) + hundred;
        result = sb.append(result).toString();
        result = result.substring(0,1).toLowerCase() + result.substring(1);
        return result;
    }

    private static String toUp(String s) {
        StringBuilder sb = new StringBuilder();
        s = s.trim();
        for (String s1 : s.split(" ")) {
            sb.append(s1.substring(0, 1).toUpperCase() + s1.substring(1));
        }
        return sb.toString();
    }

    private static void generateClass(String sourcePath, String templateName, String targetPath, Map<String, Object> params) {
        freemarker.template.Configuration config = new freemarker.template.Configuration();
        config.setObjectWrapper(new DefaultObjectWrapper());
        Template formBeanTemplate = null;
        try {
            File targetFile = new File(targetPath.substring(0, targetPath.lastIndexOf('/')));
            if (!targetFile.exists()) {
                targetFile.mkdir();
            }
            if (new File(targetPath).exists()) {
                throw new FileAlreadyExistsException(targetPath + "已存在");
            }
            File sourceFile = new File(sourcePath);
            config.setDirectoryForTemplateLoading(sourceFile);
            formBeanTemplate = config.getTemplate(templateName, "UTF-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetPath), "UTF-8"));
            formBeanTemplate.process(params, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Object> demoParam() {
        Map<String, Object> formBeanMap = new HashMap<>();
        formBeanMap.put("beanName", "testBean");
        List<Map<String, String>> paramsList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Map<String, String> tmpParamMap = new HashMap<>();
            tmpParamMap.put("paramType", "String");
            tmpParamMap.put("paramName", "param" + i);
            paramsList.add(tmpParamMap);
        }
        formBeanMap.put("params", paramsList);
        return formBeanMap;
    }

    public static void main(String[] args) {
        String title;
        String method;
        title = "";
        title = "1108. defangIPaddr";
        method = "";
        method = "    public String defangIPaddr(String address) {\n" +
                "        \n" +
                "    }";
        generateQuestion(title, method);
    }
}
