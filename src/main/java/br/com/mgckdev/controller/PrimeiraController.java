package br.com.mgckdev.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/primeiraController")
public class PrimeiraController {

  @GetMapping("/primeiroMetodo/{id}")
  public String primeiroMetodo(@PathVariable String id) {
    return "O parêmetro é " + id;
  }

  @GetMapping("metodoComQueryParams")
  public String metodoComQueryParams(@RequestParam String id) {
    return "O parêmetro com metodoComQueryParams é " + id;
  }

  @GetMapping("metodoComTodasQueryParams")
  public String x(@RequestParam Map<String, String> allParams) {
    return "O parêmetro com metodoComTodasQueryParams é " + allParams.entrySet();
  }

}
