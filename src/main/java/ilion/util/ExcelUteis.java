package ilion.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelUteis {

	public static List<Map<Integer, Object>> converterPlanilhaParaMap(InputStream inputStream, Integer numeroPlanilha) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook(inputStream);
		
		if( numeroPlanilha >= wb.getNumberOfSheets() ) {
			return null;
		}
		
		HSSFSheet sheet = wb.getSheetAt(numeroPlanilha);
		
		List<Map<Integer, Object>> planilha = new ArrayList<Map<Integer,Object>>();
		
		for (Row row : sheet) {
			
			Map<Integer, Object> linha = new LinkedHashMap<Integer, Object>();
			for (Cell cell : row) {
				Integer columnIndex = cell.getColumnIndex();
				
				Object valor = getValor(cell);
				
				if(columnIndex.intValue() == 0) {
					if(valor == null || valor.toString().trim().length() == 0) {
						break;
					}
				}
				
				linha.put(columnIndex, valor);
			}
			
			if( ! linha.isEmpty()) {
				planilha.add(linha);
			}
		}
		
		return planilha;
	}
	
	public static Object getValor(Cell cell) {
		Object retorno = null;
		
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			retorno = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			retorno = cell.getNumericCellValue();
			break;
		case Cell.CELL_TYPE_FORMULA:
			throw new RuntimeException("ha campo com formula, necessario retirar as formulas: "+cell);
		case Cell.CELL_TYPE_BOOLEAN:
			retorno = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			retorno = "";
			break;
		default:
			break;
		}
		
		return retorno;
	}
}