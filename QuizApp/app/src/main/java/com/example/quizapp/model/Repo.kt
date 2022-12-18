package com.example.quizapp.model

import javax.inject.Inject

class Repo @Inject constructor(){
    val data = arrayListOf(
        "В честь какого кота названо парадоксальное явление в квантовой механике?",
        "Какая страна омывается всеми океанами?",
        "Как называется игрок в бейсболе, который распологается на горке и кидает мяч?",
        "Какой из этих певческих голосов мужской?",
        "Как называлось сильно укрепленное сооружение внутри крепостной ограды?",
        "Какая система органов человека обеспечивает транспорт питательных веществ в клетки?",
        "Совокупность цветных полос, получающихся при прохождении света через призму - это...")

    val answersAsVariants = arrayListOf(
        arrayListOf("Чеширский кот", "Кот Баюн", "Кот Бегемот", "Кот Матроскин"),
        arrayListOf("Россия", "США", "Нет такой страны", "Автсралия"),
        arrayListOf("Питчер", "Бэттер", "Раннер", "Кэтчер"),
        arrayListOf("Контральто", "Сопрано", "Меццо-сопрано", "Баритон"),
        arrayListOf("Донжон", "Кавальер", "Цитадель", "Редюит"),
        arrayListOf("Кровообращения", "Пищеварения", "Кроветворения", "Эндокринная"),
        arrayListOf("Интерференция", "Спектр", "Дисперсия", "Дифракция"))

    val answersId = arrayListOf(1, 3, 1, 4, 3, 1, 2)
}