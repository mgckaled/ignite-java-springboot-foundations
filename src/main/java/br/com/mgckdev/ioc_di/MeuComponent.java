package br.com.mgckdev.ioc_di;

import org.springframework.stereotype.Component;

// ou @Service
@Component
public class MeuComponent {

  public String chamarMeuComponent() {
    return "Chamando meu component";
  }

}
