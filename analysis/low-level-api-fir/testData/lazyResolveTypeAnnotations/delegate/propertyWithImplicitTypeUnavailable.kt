// SKIP_WHEN_OUT_OF_CONTENT_ROOT

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@Target(
    AnnotationTarget.TYPE,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.FIELD,
    AnnotationTarget.TYPE_PARAMETER,
)
annotation class Anno(val position: String)


class Delegate: ReadWriteProperty<Any?, List<List<Int>>> {
    private companion object {
        fun explicitType(): @Anno("explicitType return type $prop") List<@Anno("explicitType nested return type $prop") List<@Anno("explicitType nested nested return type $prop") Int>> = 1
        const val prop = 0
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>) = explicitType()
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: @Anno("setValue type ref $prop") List<@Anno("setValue nested type ref $prop") List<@Anno("setValue nested nested type ref $prop") Int>>) {}
}

var property<caret>ToResolve by Delegate()
