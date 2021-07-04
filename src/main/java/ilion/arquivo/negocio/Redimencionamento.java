/*
 * Created on 10/11/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ilion.arquivo.negocio;

import java.awt.AlphaComposite;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ilion.util.Uteis;

@Service
public class Redimencionamento {
	
	static Logger logger = Logger.getLogger(Redimencionamento.class);
	
	/**
	 * 
	 */
	public Redimencionamento() {
		if(System.getProperty("java.awt.headless") == null)
			System.setProperty("java.awt.headless", "true");
	}

	public boolean ehImagemValida(InputStream input) {
		try {
			
			ImageIO.setUseCache(false);
			ImageIO.read(input);
			
		} catch (Throwable e) {
			logger.error("imagem invalida", e);
			return false;
		}
		return true;
	}

	public BufferedImage toBufferedImage(Image image, ColorModel cm) {        

		int w = image.getWidth(null);        
		int h = image.getHeight(null);       
		boolean alphaPremultiplied = cm.isAlphaPremultiplied();       
		WritableRaster raster = cm.createCompatibleWritableRaster(w, h);       
		BufferedImage result = new BufferedImage(cm, raster, alphaPremultiplied, null);
		Graphics2D g = result.createGraphics();
		g.drawImage(image, 0, 0, null);       
		g.dispose();       

		return result; 
	}

	public Map<String, Integer> reduzirGravar(InputStream input, String caminho, String nomeArq, Integer novaLargura) throws IOException, URISyntaxException {        
		BufferedImage image1= ImageIO.read(input);      

		int newWidth = (int) (novaLargura.intValue());
		int newHeight = calcularAltura(novaLargura.intValue(), image1.getHeight(), image1.getWidth());

		Image imageScaledOrig = image1.getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING);       

		BufferedImage biScaledOrig = toBufferedImage(imageScaledOrig, image1.getColorModel());

		String path = caminho + nomeArq;

		String extensao = nomeArq.substring(nomeArq.indexOf(".")+1, nomeArq.length());

		ImageIO.write(biScaledOrig, extensao, new File(path));

		HashMap<String, Integer> dimen = new HashMap<String, Integer>();
		dimen.put("largura", newWidth);
		dimen.put("altura", newHeight);

		return dimen;
	}

	public Map<String, Integer> obterDimensoes(InputStream input) throws IOException {        
		BufferedImage img = ImageIO.read(input);
		HashMap<String, Integer> dimen = new HashMap<String, Integer>();
		if(img != null){
			dimen.put("largura", img.getWidth());
			dimen.put("altura", img.getHeight());		
		}
		return dimen;
	}

	public Map<String, Integer> obterDimensoes(File file) throws IOException {        
		BufferedImage img = ImageIO.read(file);
		HashMap<String, Integer> dimen = new HashMap<String, Integer>();
		if(img != null){
			dimen.put("largura", img.getWidth());
			dimen.put("altura", img.getHeight());		
		}
		return dimen;
	}



	public void adequarFotoAmpliadaGravar(InputStream input, String caminho, String nomeArq, Integer novaLargura) throws IOException, URISyntaxException {        
		BufferedImage image1= ImageIO.read(input);      

		int newWidth = image1.getWidth();
		int newHeight = image1.getHeight();
		if(newWidth > novaLargura.intValue()) {
			newWidth = (int)(novaLargura.intValue());
			newHeight = calcularAltura(novaLargura.intValue(), image1.getHeight(), image1.getWidth());
		}

		Image imageScaledOrig = image1.getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING);       

		BufferedImage biScaledOrig = toBufferedImage(imageScaledOrig, image1.getColorModel());

		String path = caminho + nomeArq;

		String extensao = nomeArq.substring(nomeArq.indexOf(".")+1, nomeArq.length());

		ImageIO.write(biScaledOrig, extensao, new File(path));		
	}

	public int calcularAltura(int novaLargura, int alturaOriginal, int larguraOriginal) {
		return (novaLargura * alturaOriginal)/larguraOriginal;		
	}

	public BufferedImage marcaDAgua(FileInputStream imagem, FileInputStream marcaDAgua, int x, int y, float alpha) throws IOException {
		BufferedImage imagem1 = ImageIO.read(imagem);
		BufferedImage imagem2 = ImageIO.read(marcaDAgua);

		return marcaDAgua(imagem1, imagem2, x, y, alpha);
	}

	public BufferedImage marcaDAgua(BufferedImage imagem, BufferedImage marcaDAgua, int x, int y, float alpha) {
		Graphics2D g2d = imagem.createGraphics();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2d.drawImage(marcaDAgua, x, y, marcaDAgua.getWidth(), marcaDAgua.getHeight(), null);
		return imagem;
	}

	public void aplicarMarcaDAgua(Arquivo arquivo) throws Exception {
		String caminho = Uteis.caminhoFisico+"arquivos/";

		if(arquivo.getMarcaDAguaPequena() != null && !"".equals(arquivo.getMarcaDAguaPequena())) {
			FileInputStream imagem = new FileInputStream(new File(caminho+arquivo.getArquivo1()));
			FileInputStream marcaDAgua = new FileInputStream(new File(caminho+arquivo.getMarcaDAguaPequena()));

			BufferedImage bufferedImage = marcaDAgua(imagem, 
					marcaDAgua, 
					arquivo.getMarcaDAguaPequenaX(), 
					arquivo.getMarcaDAguaPequenaY(),
					arquivo.getAlphaPequena());
			
			String nomeArquivo = arquivo.getArquivo1();
			String path = caminho + nomeArquivo;
			String extensao = nomeArquivo.substring(nomeArquivo.indexOf(".")+1, nomeArquivo.length());

			ImageIO.write(bufferedImage, extensao, new File(path));
		}

		if(arquivo.getArquivo2() != null && arquivo.getArquivo2().length() != 0) {
			if(arquivo.getMarcaDAguaGrande() != null && !"".equals(arquivo.getMarcaDAguaGrande())) {
				FileInputStream imagem = new FileInputStream(new File(caminho+arquivo.getArquivo2()));
				FileInputStream marcaDAgua = new FileInputStream(new File(caminho+arquivo.getMarcaDAguaGrande()));

				BufferedImage bufferedImage = marcaDAgua(imagem, 
						marcaDAgua, 
						arquivo.getMarcaDAguaGrandeX(), 
						arquivo.getMarcaDAguaGrandeY(),
						arquivo.getAlphaGrande());

				String nomeArquivo = arquivo.getArquivo2();
				String path = caminho + nomeArquivo;
				String extensao = nomeArquivo.substring(nomeArquivo.indexOf(".")+1, nomeArquivo.length());

				ImageIO.write(bufferedImage, extensao, new File(path));
			}
		}
	}
	
	@Autowired
	ArquivoUteis arquivoUteis;

	public Arquivo aplicarCrop(Arquivo arquivo, int xRect, int yRect, int rectWidth, int rectHeight) throws Exception{

		String caminho = Uteis.caminhoFisico+"arquivos/";
		String nomeArquivo = arquivo.getArquivo1();

		FileInputStream imagem = new FileInputStream(new File(caminho+arquivo.getArquivo1()));

		BufferedImage imagem1 = ImageIO.read(imagem); 

		//imagem1 = imagem1.getSubimage(xRect, yRect, rectWidth, rectHeight);
		imagem1 = new Crop().crop(imagem1, xRect, yRect, rectWidth, rectHeight);

		String novoNome = arquivoUteis.renomear(nomeArquivo);

		String path = caminho + novoNome;

		String extensao = novoNome.substring(novoNome.indexOf(".")+1, novoNome.length());

		boolean gravou = ImageIO.write(imagem1, extensao, new File(path));
		if(gravou) {
			arquivo.setAltura(imagem1.getHeight());
			arquivo.setLargura(imagem1.getWidth());
			arquivo.setArquivo1(novoNome);

			arquivoUteis.apagarArquivo(nomeArquivo, caminho);
		}
		return arquivo;
	}

	class Crop extends Component {
		
		private static final long serialVersionUID = 1L;

		public BufferedImage crop(BufferedImage imagem, int xRect, int yRect, int rectWidth, int rectHeight) {
			Image image = createImage(new FilteredImageSource(imagem.getSource(),
					new CropImageFilter(xRect, yRect, rectWidth, rectHeight)));
			
			imagem = toBufferedImage(image, imagem.getColorModel());
			
			return imagem;
		}
	}
}