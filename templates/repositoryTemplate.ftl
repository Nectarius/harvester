
<@forAllTypes var="type" annotationVar="datatypeDefinition" annotation="javax.persistence.Entity">

<#assign repositoryPackage = "ru.bn.repository">
<#assign repositoryClassName = "${type.simpleName}Repository">

<@javaSource name="${repositoryPackage}.${repositoryClassName}">


package ${repositoryPackage};

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ${type.package}.${type.simpleName};

@Repository
public interface ${repositoryClassName} extends JpaRepository<${type.simpleName}, Long> {



}

</@javaSource>
</@forAllTypes>