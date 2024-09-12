package org.meatball.quiz.bot.categories

import org.meatball.quiz.bot.categories.handler.ArtCategoryButtonHandler
import org.meatball.quiz.bot.categories.handler.ChemistryCategoryButtonHandler
import org.meatball.quiz.bot.categories.handler.GeographyCategoryButtonHandler
import org.meatball.quiz.bot.categories.handler.QuestionsCategoryButtonHandler
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.enums.ButtonCommand

enum class MainMenuCategoryButton(
    override val key: String,
    override val service: ButtonCommandService
) : ButtonCommand {
    GEOGRAPHY("category_geography", GeographyCategoryButtonHandler()),
    ART("category_art", ArtCategoryButtonHandler()),
    CHEMISTRY("category_chemistry", ChemistryCategoryButtonHandler()),
    QUESTIONS("category_questions", QuestionsCategoryButtonHandler())
}