package components.apiNasa;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import components.ClienteHttp;
import components.Conteudo;
import components.stickers.GeradorDeFigurinhas;

public class App {
  
    public static void main(String[] args) throws Exception {

      // fazer uma conexão HTTP
      String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
      
      var http = new ClienteHttp();
      String json = http.buscaDados(url);

      // cria pasta 
      var diretorio = new File("ALURA-STICKERS/figurinhas/Nasa");
          diretorio.mkdir();

      // exibir e manipular os dados 
      ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();
      List<Conteudo> conteudos = extrator.extraiConteudos(json);

      var geradora = new GeradorDeFigurinhas();
      for (int i = 0; i < 3; i++) {

          Conteudo conteudo = conteudos.get(i);

          InputStream inputStream = new URL(conteudo.geturlImagem()).openStream();
          String nomeArquivo = "ALURA-STICKERS/figurinhas/Nasa/" + conteudo.getTitulo() + ".png";

          geradora.cria(inputStream, nomeArquivo);

          System.out.println(conteudo.getTitulo());
          System.out.println();
      }
    }
}
