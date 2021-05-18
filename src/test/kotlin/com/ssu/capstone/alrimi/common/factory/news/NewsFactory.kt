package com.ssu.capstone.alrimi.common.factory.news

import com.ssu.capstone.alrimi.api.controller.dtos.news.DetailNewsDto
import com.ssu.capstone.alrimi.api.controller.dtos.news.NewsCrawlerDto
import com.ssu.capstone.alrimi.core.util.common.DateUtil
import java.util.*

object NewsFactory {

    fun getKeywords(): List<String> {
        return listOf("ITZY", "빅뱅", "블랙핑크", "트와이스", "BTS")
    }

    fun getNewsDto(): DetailNewsDto {
        return DetailNewsDto("MockTitle", "https://mocknews.com", "mocking description", "2021-05-15")
    }

    private fun getNewsDto(title: String, text: String): DetailNewsDto {
        return DetailNewsDto(title, "https://mocknews.com", text, DateUtil.getStringFromDate(Date()))
    }

    fun getNewsCrawlerDto(): NewsCrawlerDto {
        val news = listOf(
            getNewsDto(
                "keyword1 Include News",
                "itzy는 16일 방송된 SBS '인기가요'에서 타이틀곡 '마.피.아. 인 더 모닝'으로 하이라이트의 '불어온다', 브레이브걸스의 '운전만해'를 제치고 1위 트로피를 품에 안았다.\n" +
                        "이들은 많은 분들이 힘 써주신 앨범이 많은 분들에게 사랑받아 진심으로 감사하다. 앞으로 많은 사랑 받는 가수가 되고 싶다고 밝혔다.\n" +
                        "ITZY는 4월 30일 '마.피.아. 인 더 모닝'으로 컴백했다. '마.피.아. 인 더 모닝'은 마피아 게임에 착안해 만든 곡으로 자신의 감정을 베일에 가린 채 다가가 사랑하는 사람의 마음을 송두리째 빼앗겠다는 자신감 넘치는 메시지를 담았다. ITZY는 이 곡으로 Mnet '엠카운트다운'(2회) MBC M '쇼! 챔피언'에 이어 음악방송 4관왕 기록을 썼다.\n" +
                        "이날 '인기가요'에서는 대형 가수들의 컴백무대도 꾸며졌다.\n" +
                        "NCT 드림은 정규1집 타이틀곡 '맛'과 수록곡 '고래' 무대로 에너제틱한 '칠드림' 매력을 뽐냈다. 1년 여만에 컴백을 알린 오마이걸도 타이틀곡 '던던 댄스'와 수록곡 '디어 유' 무대로 특유의 밝고 사랑스러운 에너지를 선사했다. 우주소녀의 새로운 유닛인 우주소녀 더 블랙은 신곡 '이지'로 기존에 보여주지 않았던 시크한 매력을 어필, 눈길을 끌었다."
            ),

            getNewsDto(
                "keyword Exclude News",
                "[OSEN=장우영 기자] 걸그룹 나인뮤지스 출신 배우 경리가 빨간 장미꽃 앞에서 꽃미모를 보였다.\n" +
                        "경리는 16일 자신의 인스타그램에 “안 주면 찾아가면 됨”이라는 글을 올렸다.\n" +
                        "사진에는 아파트 담벼락에 아름답게 펼쳐진 장미꽃 앞에서 포즈를 잡고 있는 경리의 모습이 담겼다.\n" +
                        "경리는 민소매 상의와 청바지로 남다른 패션 센스로 눈길을 끌었다. 사랑스러운 분위기와 미소가 감탄을 자아낸다. 특히 경리는 로즈데이에 장미를 못 받았는지 “안 주면 찾아가면 됨”이라는 센스도 보였다.\n" +
                        "한편, 경리는 현재 JTBC 금토드라마 ‘언더커버’에서 안기부 요원 고윤주의 청춘 시절을 연기 중이다. "
            )
        )
        return NewsCrawlerDto(news)
    }

}