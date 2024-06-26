/*
 * Copyright 2010-2023 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.renderer.declarations.renderers.callables

import org.jetbrains.kotlin.analysis.api.KtAnalysisSession
import org.jetbrains.kotlin.analysis.api.renderer.declarations.KtDeclarationRenderer
import org.jetbrains.kotlin.analysis.api.renderer.declarations.modifiers.renderers.KtRendererKeywordFilter
import org.jetbrains.kotlin.analysis.api.symbols.KtDestructuringDeclarationSymbol
import org.jetbrains.kotlin.analysis.utils.printer.PrettyPrinter
import org.jetbrains.kotlin.lexer.KtTokens

public interface KtDestructuringDeclarationRenderer {
    public fun renderSymbol(
        analysisSession: KtAnalysisSession,
        symbol: KtDestructuringDeclarationSymbol,
        declarationRenderer: KtDeclarationRenderer,
        printer: PrettyPrinter,
    )

    public object WITH_ENTRIES : KtDestructuringDeclarationRenderer {
        override fun renderSymbol(
            analysisSession: KtAnalysisSession,
            symbol: KtDestructuringDeclarationSymbol,
            declarationRenderer: KtDeclarationRenderer,
            printer: PrettyPrinter,
        ) {
            printer {
                declarationRenderer.codeStyle.getSeparatorBetweenAnnotationAndOwner(analysisSession, symbol).separated(
                    { declarationRenderer.annotationRenderer.renderAnnotations(analysisSession, symbol, printer) },
                    {
                        // do not render (val a: Int, val b: Int), render `(a: Int, b: Int)` instead
                        val rendererWithoutValVar = declarationRenderer.with {
                            keywordsRenderer = keywordsRenderer.with {
                                keywordFilter =
                                    keywordFilter and KtRendererKeywordFilter.without(KtTokens.VAL_KEYWORD, KtTokens.VAR_KEYWORD)
                            }
                        }
                        printCollection(symbol.entries, prefix = "(", postfix = ")") {
                            with(rendererWithoutValVar) {
                                localVariableRenderer.renderSymbol(analysisSession, it, rendererWithoutValVar, this@printCollection)
                            }
                        }
                    }
                )

            }
        }
    }
}