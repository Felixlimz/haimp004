package com.miniproject.haimp004.controller;

import com.miniproject.haimp004.service.LiveWeatherService;
import com.miniproject.haimp004.service.StubWeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

@Controller
public class CurrentWeatherController {

    private final StubWeatherService stubWeatherService;
    private final LiveWeatherService liveWeatherService;

    public CurrentWeatherController(StubWeatherService stubWeatherService, LiveWeatherService liveWeatherService) {
        this.stubWeatherService = stubWeatherService;
        this.liveWeatherService = liveWeatherService;
    }

    @GetMapping("/current-weather")
    public String getCurrentWeather(Model model) {
        if (true) {
            model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather("Jakarta", "id"));
        } else {
            model.addAttribute("currentWeather", stubWeatherService.getCurrentWeather("Jakarta", "id"));
        }
        return "current_weather";
    }
}
