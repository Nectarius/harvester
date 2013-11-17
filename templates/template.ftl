
<@forAllTypes var="type" annotationVar="datatypeDefinition" annotation="javax.persistence.Entity">

<#assign viewPackage = type.package?replace("entity","view")>
<#assign viewClassName = "Plain${type.simpleName}View">
<#assign viewSuperClassName = type.getSuperclass()>


<@javaSource name="${viewPackage}.${viewClassName}">
// -- DO NOT EDIT  -  THIS CODE WILL BE REGENERATED! --
package ${viewPackage};

<#if type.getSuperclass()= "java.lang.Object">
public class ${viewClassName} {
<#else>

public class ${viewClassName} extends Plain${type.getSuperclass().getDeclaration().simpleName}View{
</#if>

<@forAllFields var="field"  excludes="javax.persistence.ManyToOne,javax.persistence.OneToOne,javax.persistence.OneToMany">
	private ${field.type} ${field.simpleName};
</@forAllFields>

    public ${viewClassName}() {

	}
	
<@forAllFields var="field" excludes="javax.persistence.ManyToOne,javax.persistence.OneToOne,javax.persistence.OneToMany">
    <#if !field.modifiers?seq_contains("static") >
		<#if field.type = "boolean" || field.type = "java.lang.Boolean">
		public ${field.type} is${field.simpleName?cap_first}() {
			return ${field.simpleName};
		}
		<#else>
		public ${field.type} get${field.simpleName?cap_first}() {
			return ${field.simpleName};
		}
		</#if>
	
		public void set${field.simpleName?cap_first}(${field.type} ${field.simpleName}) {
			this.${field.simpleName} = ${field.simpleName};
		}
	</#if>
</@forAllFields>
}
</@javaSource>

<#assign mapperPackage = type.package?replace("entity","viewmapper")>
<@javaSource name="${mapperPackage}.${viewClassName}Mapper">
// -- DO NOT EDIT  -  THIS CODE WILL BE REGENERATED! --
package ${mapperPackage};

import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ${viewPackage}.${viewClassName};
import ${type.qualifiedName};

@Service
public class ${viewClassName}Mapper {

    <#if !(type.getSuperclass()= "java.lang.Object") >	
	@Autowired	      
	private  Plain${type.getSuperclass().getDeclaration().simpleName}ViewMapper parentMapper;			  
	</#if>

	<#assign entityVar = "${type.simpleName?uncap_first}">
	<#assign viewVar = "${viewClassName?uncap_first}">
	public ${viewClassName} create(${type.simpleName} ${entityVar}) {
		if( ${entityVar} == null ) return null;
		${viewClassName} ${viewVar} = new ${viewClassName}();
		return copyTo(${entityVar}, ${viewVar});
	}
	
	public List<${viewClassName}> createList(Collection<${type.simpleName}> source)
	{
	    List<${viewClassName}> result = new ArrayList<${viewClassName}>(source.size());
	    for (${type.simpleName} ${entityVar} : source)
	    {
	        result.add(create(${entityVar}));
	    }
		return result;
	}	
 
 	public ${viewClassName} copyTo(${type.simpleName} source, ${viewClassName} destination) {
		<@forAllFields var="field"  excludes="javax.persistence.ManyToOne,javax.persistence.OneToOne,javax.persistence.OneToMany">
			<#if !field.modifiers?seq_contains("static") >
				<#if field.type = "boolean" || field.type = "java.lang.Boolean">
				destination.set${field.simpleName?cap_first}(source.is${field.simpleName?cap_first}());
				<#else>
				destination.set${field.simpleName?cap_first}(source.get${field.simpleName?cap_first}());
				</#if>
			</#if>
			
		</@forAllFields>		
		<#if !(type.getSuperclass()= "java.lang.Object") >      
	      parentMapper.copyTo(source,destination);			  
	    </#if>		
		return destination;
	}
	
	public ${type.simpleName} copyFrom(${viewClassName} source, ${type.simpleName} destination) {
		<@forAllFields var="field"  excludes="javax.persistence.ManyToOne,javax.persistence.OneToOne,javax.persistence.OneToMany">
			<#if !field.modifiers?seq_contains("static") >
				<#if field.type = "boolean" || field.type = "java.lang.Boolean">
				destination.set${field.simpleName?cap_first}(source.is${field.simpleName?cap_first}());
				<#else>
				destination.set${field.simpleName?cap_first}(source.get${field.simpleName?cap_first}());
				</#if>
			</#if>
		</@forAllFields>
		<#if !(type.getSuperclass()= "java.lang.Object") >      
	      parentMapper.copyFrom(source,destination);			  
	    </#if>
		return destination;
	}
}
</@javaSource>
</@forAllTypes>