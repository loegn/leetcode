public class ${beanName?cap_first}Form {

<#list params as param>
    private ${param.paramType} ${param.paramName};
    public void set${param.paramName?cap_first}(${param.paramType} ${param.paramName}){
    this.${param.paramName} = ${param.paramName};
    }

    public ${param.paramType} get${param.paramName?cap_first}(){
    return this.${param.paramName};
    }

</#list>
}