package net.graphitemc.mappingutil

import java.io.File

class ClassMapping(
    val names: List<String>,
    val fields: List<FieldMapping>,
    val methods: List<MethodMapping>,
)

class FieldMapping(
    val names: List<String>,
)

class MethodMapping(
    val signature: String,
    val names: List<String>,
    val parameters: List<ParameterMapping>,
)

class ParameterMapping(
    val names: List<String>,
)


class MappingFile(private val names: List<String>, private val classMappings: List<ClassMapping>) {
    init {
        require(names.isNotEmpty()) { "Names must not be empty" }
        require(classMappings.isNotEmpty()) { "Class mappings must not be empty" }
        require(classMappings.all { classMapping -> classMapping.names.size == names.size && classMapping.fields.all { fieldMapping -> fieldMapping.names.size == names.size } && classMapping.methods.all { methodMapping -> methodMapping.names.size == names.size && methodMapping.parameters.all { parameterMapping -> parameterMapping.names.size == names.size } } }) { "All mappings must have the same number of names" }
    }

    fun write(file: File) {
        val writer = file.printWriter()
        writer.println("tj ${names.joinToString("\t")}")
        writer.println("C\t${classMappings.forEach { classMapping -> 
            classMapping.names.joinToString("\t")
        }}")
        writer.println("F\t${classMappings.forEach { classMapping -> 
            classMapping.fields.forEach { fieldMapping ->
                fieldMapping.names.joinToString("\t")
            }
        }}")
        writer.println("M\t${classMappings.forEach { classMapping -> 
            classMapping.methods.forEach { methodMapping ->
                methodMapping.names.joinToString("\t")
            }
        }}")
        writer.println("P\t${classMappings.forEach { classMapping -> 
            classMapping.methods.forEach { methodMapping ->
                methodMapping.parameters.forEach { parameterMapping ->
                    parameterMapping.names.joinToString("\t")
                }
            }
        }}")
    }
}