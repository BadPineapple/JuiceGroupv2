/*
 * Created on 03/11/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ilion.arquivo.negocio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.validation.ValidationException;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.arquivo.negocio.upload.ArquivoTipoEnum;
import ilion.util.StringUtil;
import ilion.util.Uteis;

@Service
public class ArquivoUteis {
	
	private static Logger logger = Logger.getLogger(ArquivoUteis.class);
	
	@Autowired
	private PropNegocio propNegocio;
	
	protected String pathArquivos;
	
	protected String enderecoArquivos;
	
	protected static String url;
	
	private static final String SLASH_WINDOWS = "\\";
	private static final String SLASH_LINUX = "/";
	
	
	public ArquivoUteis() {
		super();
	}
	
	public String getPathArquivos() {
		
		if( pathArquivos == null ) {
			String pathStatic = propNegocio.findValueById(PropEnum.STATIC_PATH_ABSOLUTO);
			
			if( ! pathStatic.endsWith("/") ) {
				pathStatic += "/";
			}
			
			String sufixoArquivos = propNegocio.findValueById(PropEnum.ILIONNET_ARQUIVOS_SUFIXO);
			
			pathArquivos = pathStatic + sufixoArquivos;
			
			if( ! pathArquivos.endsWith("/") ) {
				pathArquivos += "/";
			}
			
			ArquivoUteis.verificarPastaExistente(pathArquivos);
		}
		
		return pathArquivos;
	}

	public String getEnderecoArquivos() {
		
		if( enderecoArquivos == null ) {
			String urlStatic = propNegocio.findValueById(PropEnum.STATIC_URL);
			
			if( ! urlStatic.endsWith("/") ) {
				urlStatic += "/";
			}
			
			String sufixoArquivos = propNegocio.findValueById(PropEnum.ILIONNET_ARQUIVOS_SUFIXO);
			
			enderecoArquivos = urlStatic + sufixoArquivos;
			
			if( ! enderecoArquivos.endsWith("/") ) {
				enderecoArquivos += "/";
			}
			
		}
		
		return enderecoArquivos;
	}

	private void fastChannelCopy(final ReadableByteChannel src, final WritableByteChannel dest) throws IOException {  
		final ByteBuffer buffer = ByteBuffer.allocateDirect(8 * 1024);
		while (src.read(buffer) != -1) {
			// prepare the buffer to be drained
			buffer.flip();
			// write to the channel, may block
			dest.write(buffer);
			// If partial transfer, shift remainder down
			// If buffer is empty, same as doing clear()
			buffer.compact();
		}
		// EOF will leave buffer in fill state
		buffer.flip();
		// make sure the buffer is fully drained.
		while (buffer.hasRemaining()) {
			dest.write(buffer);
		}
	}

	public void gravarArquivo(InputStream in, String nomeArquivo, String caminho) throws Exception {
		File dest = new File(caminho+nomeArquivo);
		
		if(!dest.exists()) {
			dest.createNewFile();
		}
		
		OutputStream out = null;
		try {
			out = new FileOutputStream(dest);
			
			// Transfer bytes from in to out
			byte[] buf = new byte[4*1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} finally {
			if(in != null) {
				in.close();
			}
			if(out != null) {
				out.close();
			}
		}
	}

	public class CopiarArquivosThread extends Thread {

		private File fileOrigem;
		private File fileDestino;

		public CopiarArquivosThread(File fileOrigem, File fileDestino) {
			super();
			this.fileOrigem = fileOrigem;
			this.fileDestino = fileDestino;

			start();
		}

		@Override
		public void run() {
			try {
				copiarArquivo(fileOrigem, fileDestino);
			} catch (Exception e) {
				logger.error("copiando arquivos de "+fileOrigem.getPath()+" para "+fileDestino.getPath(), e);
			}
		}
	}

	public synchronized void copiarArquivo(File arquivoOrigem, File arquivoDestino) throws Exception {
		ReadableByteChannel origem = Channels.newChannel(new FileInputStream(arquivoOrigem));
		FileChannel destino = new FileOutputStream(arquivoDestino).getChannel();

		fastChannelCopy(origem, destino);

		origem.close();
		destino.close();		
	}

	public boolean apagarArquivo(String nomeArquivo, String caminho) {
		
		File file = null;		
		
		if( ArquivoTipoEnum.IMAGEM.ehExtensaoPermitida(nomeArquivo) )
			file = new File(caminho + nomeArquivo);
		else
			file = new File(caminho + "downloads/" + nomeArquivo);
		
		return file.delete();
	}

	public String obterFormato(String nomeArquivo) {
		return nomeArquivo.substring(nomeArquivo.lastIndexOf(".")+1, nomeArquivo.length());
	}

	public String obterNome(String nomeArquivo) {
		nomeArquivo = nomeArquivo.substring(0, nomeArquivo.indexOf("."));

		if(nomeArquivo.length() > 80)
			nomeArquivo = nomeArquivo.substring(0,70);

		return nomeArquivo;
	}

	public String renomearImagem_g(String nomeInicial){
		return nomeInicial.substring(0, nomeInicial.indexOf(".")) + "_g" + nomeInicial.substring(nomeInicial.indexOf("."), nomeInicial.length());		
	}

	public String renomearImagem_g(String nomeInicial, String nomeArquivo1)throws Exception{
		return nomeArquivo1.substring(0, nomeArquivo1.indexOf(".")) + "_g" + nomeInicial.substring(nomeInicial.indexOf("."), nomeInicial.length());
	}

	public String renomear(String nomeOriginal){
		String nomeAjustado = null;
		String extensao = "";

		if(nomeOriginal.indexOf(".") != -1) {
			extensao = obterFormato(nomeOriginal);
			nomeAjustado = nomeOriginal.substring(0, nomeOriginal.indexOf("."));
		} else {
			nomeAjustado = nomeOriginal;
		}

		nomeAjustado = StringUtil.ajustarTextoParaUrl(nomeAjustado);
		nomeAjustado += cincoRandomNumeros();

		if(nomeOriginal.indexOf(".") != -1) {
			nomeAjustado += "."+extensao;
		}

		return nomeAjustado;
	}

	private static String cincoRandomNumeros() {
		StringBuilder sb = new StringBuilder("-");

		Random r = new Random();
		for (int i = 0; i < 5; i++) {
			int n = r.nextInt(20);
			sb.append(n);
		}

		return sb.toString();
	}

	//	public void descompactarZip(String pathArquivoOrigem, String pathPastaDestino) throws Exception {
	//	// Open the ZIP file
	//	ZipInputStream in = new ZipInputStream(new FileInputStream(pathArquivoOrigem));
	//	in.getNextEntry().
	//	// Open the output file
	//	OutputStream out = new FileOutputStream(pathPastaDestino);

	//	// Transfer bytes from the ZIP file to the output file
	//	byte[] buf = new byte[1024];
	//	int len;
	//	while ((len = in.read(buf)) > 0) {
	//	out.write(buf, 0, len);
	//	}

	//	// Close the streams
	//	out.close();
	//	in.close();
	//	}

//	public void descompactarZip(String pathPastaArquivoOrigem, String arquivoOrigem, String pathPastaDestino, boolean preservarArvore) throws Exception {
//		FileSystemManager fsManager = VFS.getManager();
//		FileObject zipFile = fsManager.resolveFile( "zip:"+pathPastaArquivoOrigem + arquivoOrigem );
//
//		FileObject[] children = zipFile.getChildren();
//		for ( int i = 0; i < children.length; i++ ) {
//			ZipFileObject zipFileObject = (ZipFileObject) children[i];
//
//			descompactarRecursivamenteZip(pathPastaDestino, zipFileObject, preservarArvore);
//		}
//	}
//
//	private void descompactarRecursivamenteZip(String pathPasta, ZipFileObject zipFileObject, boolean preservarArvore) throws Exception {
//		if(zipFileObject.getType().hasChildren()) {
//			String pathNovaPasta = preservarArvore ? pathPasta+"/"+zipFileObject.getName().getBaseName() : pathPasta;
//			File f = new File(pathNovaPasta);
//
//			if( ! f.exists()) {
//				f.mkdirs();
//			}
//
//			if( f.exists()) {
//				FileObject[] children = zipFileObject.getChildren();
//				for ( int i = 0; i < children.length; i++ ) {
//					ZipFileObject z = (ZipFileObject) children[i];
//
//					if(z.getType().hasChildren()) {
//						descompactarRecursivamenteZip(pathNovaPasta, z, preservarArvore);
//					} else {
//						gravarArquivo(z, pathNovaPasta+"/"+z.getName().getBaseName());
//					}
//				}
//			} else {
//				logger.warn("N�O FOI POSS�VEL CRIAR A PASTA! "+pathNovaPasta);
//			}
//		} else {
//			gravarArquivo(zipFileObject, pathPasta+zipFileObject.getName().getBaseName());
//		}
//	}
//
//	private void gravarArquivo(FileObject fileObject, String path) throws Exception {
//		FileUtil.writeContent(fileObject, new FileOutputStream(path));
//	}

	//	public static void main(String[] args) {
	//	String pathPastaOrigem = "/home/jan/lixo/imagens/sites/";
	//	String arquivoOrigem = "housegarden2Zip.zip";
	//	//String arquivoOrigem = "imagesZip.zip";
	//	String pathPastaDestino = "/home/jan/workspace/ilionsite/web/arquivos/downloads/arquivosEmMassa/";

	//	ArquivoUteis a = new ArquivoUteis();

	//	//String nomeArquivo1 = a.renomearImagemDownload("imagesZip.zip");
	//	//nomeArquivo1 = "emMassa" + nomeArquivo1;

	//	try {
	//	a.descompactarZip(pathPastaOrigem, arquivoOrigem, pathPastaDestino, true);
	//	} catch (Exception e) {
	//	e.printStackTrace();
	//	}
	//	}

	public static Boolean existeJspSite(String pagina) {
		File a = new File(Uteis.caminhoFisico+pagina+".jsp");

		return a.exists();
	}
	
	public static Boolean existeArquivo(String path) {
		File a = new File(path);

		return a.exists();
	}

	public static void escreverParaArquivo(String s, String arquivo) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo));
			bw.append(s);
			bw.close();
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	public static String lerConteudoArquivo(String arquivo, Boolean withLineSeparator) {
		return lerConteudoArquivo(new File(arquivo), withLineSeparator);
	}
	
	public static String lerConteudoArquivo(File file, Boolean withLineSeparator) {
		StringBuilder conteudo = new StringBuilder();
		try {
			BufferedReader bin = new BufferedReader(new FileReader( file ));
			String line;
			while ( (line = bin.readLine()) != null ) {
				conteudo.append(line);
				
				if( withLineSeparator ) {
					conteudo.append(System.getProperty("line.separator"));
				}
			}
			bin.close();
		} catch (Exception e) {
			String m = "lerConteudoArquivo";
			logger.error(m, e);
		}
		return conteudo.toString();
	}
	
	public static String lerInputStream(InputStream inputStream) {
		StringBuilder conteudo = new StringBuilder();
		try {
			BufferedReader bin = new BufferedReader(new InputStreamReader( inputStream ));
			String line;
			while ( (line = bin.readLine()) != null ) {
				conteudo.append(line);
			}
			bin.close();
		} catch (Exception e) {
			String m = "lerInputStream";
			logger.error(m, e);
		}
		return conteudo.toString();
	}
	
	public static void verificarPastaExistente(String path) {
		File pasta = new File(path);
		
		if( ! pasta.exists() ) {
			pasta.mkdirs();
		}
	}
	
	public void escreverArquivo(InputStream inputStream, String pathDestino) throws FileNotFoundException {
		try {
			FileOutputStream streamOut = new FileOutputStream(new File(pathDestino));
			
			int c;
			while ((c = inputStream.read()) != -1) {
				streamOut.write(c);
			}
			
			inputStream.close();
			streamOut.close();
		} catch (FileNotFoundException e) {
			String m = "Erro ao escreverArquivo: "+pathDestino;
			logger.error(m, e);
		} catch (IOException e) {
			String m = "Erro ao escreverArquivo: "+pathDestino;
			logger.error(m, e);
		}
	}
	
	public void excluirArquivo(String path) throws Exception {
		try {
			File file = new File(path);
			file.delete();
		} catch (Exception e) {
			String m = "Erro ao escreverArquivo: "+path;
			logger.error(m, e);
		}
	}
	
	public static Boolean verificarArquivoExistente(String path) {
		File f = new File(path);
		
		return f.exists();
	}
	
	public synchronized static void excluirPastaEconteudo(File f) {
		File[] arquivos = f.listFiles();
		
		if(arquivos != null) {
			for (int i = 0; i < arquivos.length; i++) {
				File file = arquivos[i];
				if(file.isDirectory()) {
					excluirPastaEconteudo(file);
				}
				
				file.delete();
			}
		}
		
		f.delete();
	}
	
	String[] extensoesImagens = {"jpg","JPG","jpeg","JPEG","gif","GIF","png","PNG"};

	public boolean ehImagem(String nomeArquivo) {
		String formato = obterFormato(nomeArquivo);

		for(int i=0;i<extensoesImagens.length;i++)
			if(formato.equals(extensoesImagens[i]))
				return true;

		return false;
	}
	
	public static String lerConteudoArquivo(String arquivo) {
		return lerConteudoArquivo(new File(arquivo));
	}

	public static String lerConteudoArquivo(File file) {
		StringBuilder conteudo = new StringBuilder();
		try {
			BufferedReader bin = new BufferedReader(new FileReader( file ));
			String line;
			while ( (line = bin.readLine()) != null ) {
				conteudo.append(line);
			}
			bin.close();
		} catch (Exception e) {
			String m = "lerConteudoArquivo";
			logger.error(m, e);
		}
		return conteudo.toString();
	}
	
	public void decodeBase64ToFile(String base64Image, String pathFile) {
		//logger.info("BASE64 Image:  " + base64Image);
		try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
			byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
			imageOutFile.write(imageByteArray);
		} catch (FileNotFoundException e) {
			logger.info("Image not found" + e);
		} catch (IOException ioe) {
			logger.error("Exception while reading the Image ", ioe);
		}
	}
	
	 public  List<Arquivo> uploadArquivos(List<MultipartFile> arquivos, String caminho, String pacote) {

		    List<Arquivo> ar = new ArrayList<>();

		    for (MultipartFile file : arquivos) {
		      String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		      String fileName = FilenameUtils.removeExtension(file.getOriginalFilename()).concat("_").concat(String.valueOf(new Date().getTime())).concat(".").concat(extension);
		      java.io.File basePath = new java.io.File(caminho.concat(SLASH_LINUX).concat(pacote).concat(SLASH_LINUX));
		      if (!basePath.exists()) {
		        basePath.mkdirs();
		      }
		      Path newPath = Paths.get(caminho + SLASH_LINUX + pacote + SLASH_LINUX + fileName);
		      try {

		        if (!file.getOriginalFilename().isEmpty()) {
		          Files.copy(file.getInputStream(), newPath);
		        } else {
		          throw new ValidationException("Arquivo inválido.");
		        }
		      } catch (IOException e) {
		        throw new ValidationException("Houve um erro: " + e.getMessage());
		      }
		      String staticPath =  getUrl() +"/static/arquivos/" + pacote;
		      Arquivo arquivo = new Arquivo();
		      arquivo.setNomeClasse(pacote);
		      arquivo.setArquivo1(fileName);
		      arquivo.setLink(staticPath.concat("/").concat(fileName));
		      arquivo.setData(new Date().toString());
		      arquivo.setNomeArquivoOriginal(file.getOriginalFilename());
		      arquivo.setDiretorio(basePath.toString());
		      arquivo.setOpcao(extension);
		      arquivo.setLayout("destaque");
		      arquivo.setTipo(new Byte("1"));
		      ar.add(arquivo);
		    }
		    return ar;
		  }
		
	 public String getUrl() {
				if (url == null) {
				 String teste =	propNegocio.findValueById(PropEnum.URL);
				  return teste;
				}
				return url;
			}
}