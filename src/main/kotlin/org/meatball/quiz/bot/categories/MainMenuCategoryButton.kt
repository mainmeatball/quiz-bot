package org.meatball.quiz.bot.categories

import org.meatball.quiz.bot.categories.art.ArtCategoryButtonHandler
import org.meatball.quiz.bot.categories.chemistry.ChemistryCategoryButtonHandler
import org.meatball.quiz.bot.categories.geography.GeographyCategoryButtonHandler
import org.meatball.quiz.bot.categories.questions.QuestionsCategoryButtonHandler
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.button.ButtonCommand

enum class MainMenuCategoryButton(
    override val key: String,
    override val service: ButtonCommandService
) : ButtonCommand {
    GEOGRAPHY("category_geography", GeographyCategoryButtonHandler()),
    ART("category_art", ArtCategoryButtonHandler()),
    CHEMISTRY("category_chemistry", ChemistryCategoryButtonHandler()),
    QUESTIONS("category_questions", QuestionsCategoryButtonHandler())
}