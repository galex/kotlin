/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.resolve

import org.jetbrains.kotlin.config.LanguageVersionSettings
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.UnwrappedType
import org.jetbrains.kotlin.types.WrappedTypeFactory

interface DeclarationReturnTypeSanitizer {
    fun sanitizeReturnType(
            inferred: UnwrappedType,
            wrappedTypeFactory: WrappedTypeFactory,
            trace: BindingTrace,
            languageVersionSettings: LanguageVersionSettings
    ): UnwrappedType

    object Default : DeclarationReturnTypeSanitizer {
        override fun sanitizeReturnType(
                inferred: UnwrappedType,
                wrappedTypeFactory: WrappedTypeFactory,
                trace: BindingTrace,
                languageVersionSettings: LanguageVersionSettings
        ) = inferred
    }
}