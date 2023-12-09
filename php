<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_FILES['pdfFile'])) {
    $pdfFilePath = $_FILES['pdfFile']['tmp_name'];
    $outputTxtFilePath = 'output.txt';

    // Call the Java class to convert PDF to TXT
    $command = 'java -cp src/main/java PdfToTxtConverter ' . escapeshellarg($pdfFilePath) . ' ' . escapeshellarg($outputTxtFilePath);
    exec($command);

    // Display the result or perform further actions
    echo "<h2>PDF to TXT Conversion completed successfully.</h2>";
} else {
    echo "Invalid request";
}
?>
