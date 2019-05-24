package ${packageName};

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
<#list imports as import>
import ${import};
</#list>

/**
 * @date : ${date}
 * @author: ${author}
 */
public class ${className} {
    public ${returnName} result1(<#list params as param>${param.type} ${param.name}<#if param_has_next>, </#if></#list>) {
        <#if returnName != "void">
        ${returnName} result = ${returnValue};
        return result;
        </#if>
    }

    @Test
    public void ${methodName}() {
        <#list params as param>
            <#if param.extra?has_content>
        ${param.extra}
            </#if>
        </#list>
        <#list params as param>
        ${param.type} ${param.name} = ${param.value};
        </#list>
        <#if returnName != "void">
        Object result = result1(<#list params as param>${param.name}<#if param_has_next>, </#if></#list>);
        PrintUtils.print(result);
        <#else>
        result1(<#list params as param>${param.name}<#if param_has_next>, </#if></#list>);
        PrintUtils.print();
        </#if>
    }
}
