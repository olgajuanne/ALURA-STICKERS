package components.apiNasa;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import components.API;
import components.ClienteHttp;
import components.Conteudo;
import components.stickers.GeradorDeFigurinhas;

public class App {
  
    public static void main(String[] args) throws Exception {

        API api = API.LINGUAGEM;

      // fazer uma conexão HTTP
      String url = api.getUrl();
      ExtratorDeConteudoLinguagem extrator = (ExtratorDeConteudoLinguagem) api.getExtrator();
      
      var http = new ClienteHttp();
      String json = http.buscaDados(url);

      // cria pasta 
      var diretorio = new File("figurinhas/");
          diretorio.mkdir();

      // exibir e manipular os dados 

      List<Conteudo> conteudos = extrator.extraiConteudos(json);

      var geradora = new GeradorDeFigurinhas();
      for (int i = 0; i < 3; i++) {

          Conteudo conteudo = conteudos.get(i);

          InputStream inputStream = new URL(conteudo.geturlImagem()).openStream();
          String nomeArquivo = "figurinhas/" + conteudo.getTitulo() + ".png";

          geradora.cria(inputStream, nomeArquivo);

          System.out.println(conteudo.getTitulo());
          System.out.println();
      }
    }
}
