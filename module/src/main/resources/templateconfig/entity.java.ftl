package ${package.Entity};
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class ${entity} {
<#list table.fields as field>
    <#if field.propertyType == "Byte"|| field.propertyType == "Long">
        private Integer ${field.propertyName};
    <#else>
        private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
}