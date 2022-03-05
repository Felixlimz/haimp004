package com.miniproject.haimp004.controller;

import com.miniproject.haimp004.service.LiveWeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CurrentWeatherController {

    private final LiveWeatherService liveWeatherService;

    public CurrentWeatherController(LiveWeatherService liveWeatherService) {
        this.liveWeatherService = liveWeatherService;
    }

    @GetMapping("/current-weather")
    public String getCurrentWeather(Model model) {
        model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather("Jakarta", "id"));
        return "current_weather";
    }
}
