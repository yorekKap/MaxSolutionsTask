<?php
require_once 'pb_message.php';

require_once 'proto_software.php';

require_once 'proto_developer.php';

$title =  "title";
$number_of_lines = 300;
$language = "JAVA";

$software = new Software();
$software -> set_id(1);
$software->set_language(constant("Software_ProgrammingLanguage::" . $language));
$software->set_linesOfCode($number_of_lines);
$software->set_title(title);


$res = $software->SerializeToString();

$data = array("software" => $res);

$myCurl = curl_init();
curl_setopt_array($myCurl, array(
		CURLOPT_URL => 'http://localhost:8080/maxsolutions-task/software-proccess',
		CURLOPT_RETURNTRANSFER => true,
		CURLOPT_POST => true,
		CURLOPT_POSTFIELDS => http_build_query($data)
));

$response = curl_exec($myCurl);
curl_close($myCurl);

$ndev = new Developer();

$ndev->ParseFromString($response);

echo "This is the developer that you need:\n";

echo "Name:" . $ndev->name() . "\n";
echo "Phone:" . $ndev->phone() . "\n";
echo "Programming languages:" . "\n";
for($i = 0; $i < $ndev->knownLanguages_size(); $i++){
	switch ($ndev->knownLanguages($i)) {
		case 1:
			echo "Java";
			break;
		case 2:
			echo "Python";
			break;
		case 3:
			echo "Scala";	
			break;
		case 4:
			echo "JavaScript";		
	}
	
	echo " ";
	
}



?>

