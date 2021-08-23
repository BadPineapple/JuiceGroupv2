package ilion.vitazure.enumeradores;

public enum ConselhoProfissionalEnum {

  NAO_INFORMADO("", "", ""),
  ACRE("CRP-20 20a Região", "CRM-AC", "Acre"),
  ALAGOAS("CRP-AL 15a Região", "CRM-AL","Alagoas"),
  AMAPA("CRP-10 10a Região", "CRM-AP", "Amapá"),
  AMAZONAS("CRP-20 20a Região", "CRM-AM", "Amazonas"),
  BAHIA("CRP-BA 3a Região", "CRM-BA", "Bahia"),
  CEARA("CRP-CE 11a Região", "CRM-CE", "Ceará"),
  DISTRITO_FEDERAL("CRP-DF 1a Região", "CRM-DF", "Distrito Federal"),
  ESPIRITO_SANTO("CRP-ES 16a Região", "CRM-ES", "Espírito Santo"),
  GOIAS("CRP 9a Região", "CRM-GO", "Goiás"),
  MARANHAO("CRP-MA 22a Região", "CRM-MA", "Maranhão"),
  MATO_GROSSO("CRP-MT 18a Região", "CRM-MT", "Mato Grosso"),
  MATO_GROSSO_DO_SUL("CRP-MS 14a Região", "CRM-MS", "Mato Grosso do Sul"),
  MINAS_GERAIS("CRP-MG 4a Região", "CRM-MG", "Minas Gerais"),
  PARA("CRP-10 10a Região", "CRM-PA", "Pará"),
  PARAIBA("CRP-PB 13a Região", "CRM-PB", "Paraíba"),
  PARANA("CRP-PR 8a Região", "CRM-PR", "Paraná"),
  PERNAMBUCO("CRP-PE 2a Região", "CRM-PE", "Pernambuco"),
  PIAUI("CRP-PI 21a Região", "CRM-PI", "Piauí"),
  RIO_DE_JANEIRO("CRP-RJ 5a Região", "CRP-RJ", "Rio de Janeiro"),
  RIO_GRANDE_DO_NORTE("CRP-RN 17a Região", "CRM-RN", "Rio Grande do Norte"),
  RIO_GRANDE_DO_SUL("CRP-RS 7a Região", "CRM-RS", "Rio Grande do Sul"),
  RONDONIA("CRP-20 20a Região", "CRM-RO", "Rondônia"),
  RORAIMA("CRP-20 20a Região", "CRM-RR", "Roraima"),
  SANTA_CATARINA("CRP-SC 12a Região", "CRM-SC", "Santa Caratina"),
  SAO_PAULO("CRP-SP 6a Região", "CRM-SP", "São Paulo"),
  SERGIPE("CRP-SE 19a Região", "CRM-SE", "Sergipe"),
  TOCANTINS("CRP 23a Região", "CRM-TO", "Tocantins");

  private String CRP;

  private String CRM;

  private String valor;

  public String getCRP() {
    return CRP;
  }

  public String getCRM() {
    return CRM;
  }

  public String getValor() {
    return valor;
  }

  private static final ConselhoProfissionalEnum VALUES[] = ConselhoProfissionalEnum.values();

  private ConselhoProfissionalEnum (String crp, String crm, String valor) {
    this.CRP = crp;
    this.CRM = crm;
    this.valor = valor;
  }

  public static ConselhoProfissionalEnum fromString(String nome) {
    for (ConselhoProfissionalEnum item : VALUES) {
      if( item.CRP.equals(nome) || item.valor.equals(nome) || item.CRM.equals(nome)){
        return item;
      }
    }
    return null;
  }

}
