package ilion.contato.controller;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class FormulariosControllerTest {
//
//	@Autowired
//	private WebApplicationContext wac;
//	
//	private MockMvc mockMvc;
//	
//	@Before
//	public void setup() {
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//	}
//	
//	@Test
//	public void deveTestarFormularioNewsletter() throws Exception {
//		
//		Map<String, String> contato = new HashMap<String, String>();
//		contato.put("nome", "Nome de Teste");
//		contato.put("email", "email@deteste.com.br");
//		
//		String json = GSONUteis.objectToJson(contato);
//		
//		this.mockMvc.perform(
//				post("/rest/newsletter")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(json)
//				)
//		.andExpect(status().isOk())
//		.andDo(print())
//		.andExpect(jsonPath("$.status").value(RespostaStatusEnum.SUCESSO.toString()))
//		;
//		
//	}
//	
//	@Test
//	public void deveTestarFormularioNewsletterSemNome() throws Exception {
//		
//		Map<String, String> contato = new HashMap<String, String>();
//		
//		String json = GSONUteis.objectToJson(contato);
//		
//		this.mockMvc.perform(
//				post("/rest/newsletter")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(json)
//				)
//		.andExpect(status().isOk())
//		.andDo(print())
//		.andExpect(jsonPath("$.status").value(RespostaStatusEnum.ERRO.toString()))
//		.andExpect(jsonPath("$.mensagem").value("Nome deve ser preenchido."))
//		;
//		
//	}
//	
//	@Test
//	public void deveTestarFormularioNewsletterSemEmail() throws Exception {
//		
//		Map<String, String> contato = new HashMap<String, String>();
//		contato.put("nome", "Nome de Teste");
//		
//		String json = GSONUteis.objectToJson(contato);
//		
//		this.mockMvc.perform(
//				post("/rest/newsletter")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(json)
//				)
//		.andExpect(status().isOk())
//		.andDo(print())
//		.andExpect(jsonPath("$.status").value(RespostaStatusEnum.ERRO.toString()))
//		.andExpect(jsonPath("$.mensagem").value("Email inválido."))
//		;
//		
//	}
//	
//	@Test
//	public void deveTestarFormularioNewsletterEmailInvalido() throws Exception {
//		
//		Map<String, String> contato = new HashMap<String, String>();
//		contato.put("nome", "Nome de Teste");
//		contato.put("email", "nome");
//		
//		String json = GSONUteis.objectToJson(contato);
//		
//		this.mockMvc.perform(
//				post("/rest/newsletter")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(json)
//				)
//		.andExpect(status().isOk())
//		.andDo(print())
//		.andExpect(jsonPath("$.status").value(RespostaStatusEnum.ERRO.toString()))
//		.andExpect(jsonPath("$.mensagem").value("Email inválido."))
//		;
//		
//	}
//}
