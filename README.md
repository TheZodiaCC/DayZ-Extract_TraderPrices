# DayZ-Extract_TraderPrices
# Extract Trader Prices using Tesseract OCR

# **Updated Version That Can Handle Multiple Input Files**

## Installation
- download opencsv and tess4j and include them to project
- change path for pathToCSV, fileName, dirPath, ExtractText.setDataSet to match path for your files
- run "javac Main.java" to compile project
- run "java Main.java" to run script

## Usage
- Input Image resolution has to be 1920x1080
- **To prevent duplicate values trim the pictures so that they don't have the same elements** 
![Example Input Image](https://raw.githubusercontent.com/TheZodiaCC/DayZ-Extract_TraderPrices/master/exampleimg.png)
- Run script
- Output will be saved to CSV file and looks like this

| Name          | Buy Price     |Sell Price |
| ------------- | ------------- |-----------|
| Brass Knuckles| 2500          |800        |
| Stun Baton    | 1500          |500        |

etc
