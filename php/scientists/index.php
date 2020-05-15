<?php

/**
 * Let's Create a class to hold our database constants.
 */
class Constants{
    //DATABASE CREDENTIALS
    static $DB_SERVER="localhost";
    static $DB_NAME="projectsdb";
    static $USERNAME="demo";
    static $PASSWORD="9AmaFn6jpmNe8DN6";

    //SQL STATEMENT
    static $SQL_SELECT_ALL="SELECT * FROM scientiststb";
}

/**
 * This class will contain methods to receive our http requests, manipulate the
 *  database and send
 * result back to the client
 */
class Scientists{
    /**
     * 1. CONNECT TO DATABASE.
     * 2. RETURN CONNECTION OBJECT
     * 3. IF NO CONNECTION THEN RETURN NULL.
     */
    public function connect()
    {
        $con=new mysqli(Constants::$DB_SERVER,Constants::$USERNAME,Constants::$PASSWORD,
        Constants::$DB_NAME);
        if($con->connect_error) {
            return null;
        }else{
            return $con;
        }
    }

    /**
     * 1. Receieve data from our HTTP POST Request.
     * 2. Connect to mysql database.
     * 3. Save those data to mysql database.
     * 4. Return a response to the client.
     */
    public function insert(){
        $name = $_POST['name'];
 	    $description = $_POST['description'];
 	    $galaxy = $_POST['galaxy'];
		$star = $_POST['star'];
	    $dob = $_POST['dob'];
        $died = $_POST['died'];

        $con=$this->connect();
        if($con != null)
        {
            $sql = "INSERT INTO scientiststb (name, description, galaxy,star, dob, died) VALUES
            ('$name','$description','$galaxy','$star','$dob','$died')";
            $result = $con->query($sql);
            if($result == TRUE){
                 print(json_encode(array('code' =>1, 'message' => 'Data Successfully Inserted')));
            }else{
                print(json_encode(array('code' =>2,
                'message' => 'Unable to INSERT Data. However Connection was successful')));
            }
            $con->close();
        }else{
            print(json_encode(array('code' =>3,
            'message' => 'ERROR: PHP WAS UNABLE TO CONNECT TO MYSQL DUE TO NULL CONNECTION.')));
        }
    }
    /**
     * This method will:
     * 1. Receiev data from the HTTP POST request of the client
     * 2. Connect to mysql and update the id specified.
     * 3. Return a response to the client.
     */
    public function update(){
		$id = $_POST['id'];
        $name = $_POST['name'];
 	    $description = $_POST['description'];
 	    $galaxy = $_POST['galaxy'];
		$star = $_POST['star'];
	    $dob = $_POST['dob'];
        $died = $_POST['died'];

        $con=$this->connect();
        if($con != null){
 	        $sql = "UPDATE  scientiststb SET name = '$name',description = '$description',
             galaxy = '$galaxy', star = '$star', dob = '$dob',died = '$died' WHERE id='$id'";

            $result = $con->query($sql);
            if($result == TRUE){
                print(json_encode(array('code' =>1, 'message' => 'Data Successfully Updated')));
            }else{
                print(json_encode(array('code' =>2,
                'message' => 'Unable to UPDATE Data. However Connection was successful')));
            }
            $con->close();
        }else{
            print(json_encode(array('code' =>3,
            'message' => 'ERROR: PHP WAS UNABLE TO CONNECT TO MYSQL DUE TO NULL CONNECTION.')));
        }
    }
    /**
     * This method will delete the row with the specified id.
     * 1. Connect to MySQL.
     * 2. Receive the id from our HTTP request.
     * 3. Delete the row with that id.
     * 4. Return a response.
     */
    public function delete(){
        $id = $_POST['id'];

        $con=$this->connect();
        if($con != null){
            $sql = "DELETE FROM scientiststb WHERE id ='$id'";
            $result = $con->query($sql);
            if($result == TRUE){
                print(json_encode(array('code' =>1, 'message' => 'Data Successfully Deleted')));
            }else{
                print(json_encode(array('code' =>2,
                'message' => 'Unable to DELETE Data. However Connection was successful')));
            }
            $con->close();

        }else{
            print(json_encode(array('code' =>3,
            'message' => 'ERROR: PHP WAS UNABLE TO CONNECT TO MYSQL DUE TO NULL CONNECTION.')));
        }
    }
    /**
     * This method will:
     * 1. Connect to MySQL.
     * 2. Select all data from database.
     * 3. Return those data as a response.
     */
    public function select()
    {
        $con=$this->connect();
        if($con != null)
        {
            $result=$con->query(Constants::$SQL_SELECT_ALL);
            if($result->num_rows > 0)
            {
                $scientists = array();
                while($row=$result->fetch_array())
                {
                    array_push($scientists, array("id"=>$row['id'],"name"=>$row['name'],
					"description"=>$row['description'],"galaxy"=>$row['galaxy'],"star"=>$row['star'],
					"dob"=>$row['dob'],"died"=>$row['died']));
                }
                print(json_encode(array("code" => 1,"message"=>"Success", "result"=>$scientists)));
            }else{
                print(json_encode(array("code" => 0, "message"=>"Data Not Found")));
            }
            $con->close();

        }else{
            print(json_encode(array('code' =>3,
            'message' => 'ERROR: PHP WAS UNABLE TO CONNECT TO MYSQL DUE TO NULL CONNECTION.')));
        }
    }
    /**
     * This method will:
     * 1. Receive a HTTP POST request from a client.
     * 2. Get the query,limit and start data from that request.
     * 3. Perform a paginated search against our mysql database after connecting.
     * 4. Return a response with either data or error message.
     */
    public function search()
    {
		$query=$_POST['query'];
		$limit=$_POST['limit'];
        $start=$_POST['start'];

		$sql="SELECT * FROM scientiststb WHERE name LIKE '%$query%' OR galaxy LIKE '%$query%'
         LIMIT
         $limit OFFSET $start ";

        $con=$this->connect();
        if($con != null)
        {
            $result=$con->query($sql);
            if($result->num_rows>0)
            {
                $scientists=array();
                while($row=$result->fetch_array())
                {
                    array_push($scientists, array("id"=>$row['id'],"name"=>$row['name'],
                    "description"=>$row['description'],"galaxy"=>$row['galaxy'],"star"=>$row['star'],
					"dob"=>$row['dob'],"died"=>$row['died']));
                }
                print(json_encode(array("code" => 1, "message"=>"Success", "result"=>$scientists)));
            }else{
                print(json_encode(array("code" => 0, "message"=>"Data Not Found")));
            }
            $con->close();

        }else{
            print(json_encode(array('code' =>3,
            'message' => 'ERROR: PHP WAS UNABLE TO CONNECT TO MYSQL DUE TO NULL CONNECTION.')));
        }
    }
    /**
     * This method will handle our HTTP Requests.
     * Basically it checks the request type and then determines the method that needs to be
     * executed. It's kind of a controller.
     */
    public function handleRequest() {

        if($_SERVER['REQUEST_METHOD'] == 'POST')
        {
            if (isset($_POST['action'])) {

                $action=$_POST['action'];

                if($action == 'INSERT'){
                    $this->insert();
                }else if($action == 'UPDATE'){
                    $this->update();
                }else if($action == 'DELETE'){
                    $this->delete();
				}else if($action == 'GET_PAGINATED'){
                    $this->search();
				}else if($action == 'GET_PAGINATED_SEARCH'){
                    $this->search();
                }else{
					print(json_encode(array('code' =>4, 'message' => 'INVALID REQUEST.')));
				}
            } else{
				print(json_encode(array('code' =>5, 'message' => 'POST TYPE UNKNOWN.')));
            }

        }else{
            $this->select();
        }
    }
}

//Outside the class. Instantiate Scientist then invoke the handleRequest() to listen to our requests.
$s=new Scientists();
$s->handleRequest();

//end


