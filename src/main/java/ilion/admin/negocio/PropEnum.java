package ilion.admin.negocio;

import org.apache.log4j.Logger;

public enum PropEnum {

  CLIENTE, 
  SITE, // (default, caso tenha mais do que um)
  URL, 
  URL_REDE_LOCAL,
  NOME_EMPRESA,
  PATH_LUCENE,
  STATIC_PATH_ABSOLUTO,
  STATIC_URL,
  ILIONNET_ARQUIVOS_SUFIXO,
  
  EMAILS_CONTATO,
  EMAIL_DUVIDA,
  VELOCITY_PATH,
  
  JWT_SECRECT_KEY,
  
  GOOGLE_MAPS_KEY,
  PAGAR_ME_API_KEY,
  PAGAR_ME_ENCRYPTION_KEY,
  
  API_KEY_WHEREBY,
  URL_API_WHEREBY,
  SITUACAO_PAGARME

  ;

  public static PropEnum fromString(String s) {

    PropEnum retorno = null;

    for (PropEnum pEnum : values()) {

      if (pEnum.name().equalsIgnoreCase(s) || pEnum.toString().equalsIgnoreCase(s)) {
        retorno = pEnum;
      }

    }

    if (retorno == null) {
      String m = "Prop. \"" + s + "\" nao definido.";
      Logger.getLogger(PropEnum.class).error(m);
    }

    return retorno;
  }

}