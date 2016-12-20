package com.esri

import java.io.StringReader

import com.univocity.parsers.csv.{CsvParser, CsvParserSettings}

class CSVReader2(parser: CsvParser) extends Serializable {
  def parseCSV(line: String): Array[String] = {
    parser.beginParsing(new StringReader(line))
    val parsed = parser.parseNext()
    parser.stopParsing()
    parsed
  }
}

object CSVReader2 {
  def apply(fieldSep: Char = ',',
            lineSep: String = "\n",
            quote: Char = '"',
            escape: Char = '\\',
            ignoreLeadingSpace: Boolean = true,
            ignoreTrailingSpace: Boolean = true,
            inputBufSize: Int = 128,
            maxCols: Int = 20480): CSVReader2 = {

    val settings = new CsvParserSettings()
    val format = settings.getFormat
    format.setDelimiter(fieldSep)
    format.setLineSeparator(lineSep)
    format.setQuote(quote)
    format.setQuoteEscape(escape)
    settings.setIgnoreLeadingWhitespaces(ignoreLeadingSpace)
    settings.setIgnoreTrailingWhitespaces(ignoreTrailingSpace)
    settings.setReadInputOnSeparateThread(false)
    settings.setInputBufferSize(inputBufSize)
    settings.setMaxColumns(maxCols)
    settings.setNullValue("")
    settings.setEmptyValue("")

    new CSVReader2(new CsvParser(settings))
  }
}