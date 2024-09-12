package org.meatball.quiz.bot.commons


import org.meatball.quiz.bot.categories.MainMenuCategoryButton
import org.meatball.quiz.bot.categories.geography.GeographyCategoryButtonCommandManager
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Update

class ButtonCommandManager {

    fun handle(update: Update): SendMessageResponse {
        val cbQuery = update.callbackQuery

        // Main menu categories: Geography, Art, Chemistry, Questions
        doHandle(MainMenuCategoryButton.GEOGRAPHY.service, cbQuery)?.let { return it }
        doHandle(MainMenuCategoryButton.ART.service, cbQuery)?.let { return it }
        doHandle(MainMenuCategoryButton.CHEMISTRY.service, cbQuery)?.let { return it }
        doHandle(MainMenuCategoryButton.QUESTIONS.service, cbQuery)?.let { return it }

        geographyCategoryManager.handle(update)?.let { return it }

        return SendMessageResponse(emptyList())
    }

    private companion object {
        private val geographyCategoryManager = GeographyCategoryButtonCommandManager()
    }
}

fun doHandle(service: ButtonCommandService, cbQuery: CallbackQuery): SendMessageResponse? {
    if (service.suitableFor(cbQuery)) {
        return service.getResponse(cbQuery)
    }
    return null
}