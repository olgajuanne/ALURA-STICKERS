package components;

import components.apiNasa.ExtratorDeConteudoDaNasa;
import components.apiNasa.ExtratorDeConteudoDoIMDB;
import components.apiNasa.ExtratorDeConteudoLinguagem;

public enum API {
    IMDB_TOP_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json", new ExtratorDeConteudoDoIMDB()),
    NASA("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD-JamesWebbSpaceTelescope.json", new ExtratorDeConteudoDaNasa()),
    LINGUAGEM("http://localhost:8080/linguagens", new ExtratorDeConteudoLinguagem());

    private String url;
    private ExtratorDeConteudo extrator;

    API(String url, ExtratorDeConteudo extrator) {
      this.url = url;
      this.extrator = extrator;
    }

    public String getUrl() {
      return url;
    }

    public ExtratorDeConteudo getExtrator() {
      return extrator;
  }
}
