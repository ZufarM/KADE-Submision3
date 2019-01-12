package com.zufar.submision3.view.match

import android.util.Log
import com.google.gson.Gson
import com.zufar.submision3.api.ApiRepository
import com.zufar.submision3.api.TheSportDBApi
import com.zufar.submision3.model.MatchResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter(private val view: MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson) {

    fun getLastMatchEvent(){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLastMatch()),
                MatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(data.events)
                Log.e("Match Last", data.events.toString())
            }
        }
    }

    fun getNextMatchEvent(){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextMatch()),
                MatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(data.events)
                Log.e("Match Next", data.events.toString())
            }
        }
    }
}