<!DOCTYPE html>
<html lang='en'>
<head>
    <link rel='stylesheet' type='text/css' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    
</head>

<body>
<p>
<h3>How to Test</h3>
<p>1. First add a user</p>
<p>2. Then to retrieve the information back by enter the id in the Get Person session.</p>
<p>3. To test deletion, enter the id into the input box</p>
<p>4. To test exception, choose to enter a invalid Id in the Get or Deletion session.</p>
<p>Note: no validation implemented though</p>

</p>
    <div class='container'>
        <h3>Save Person</h3>
        Name:<br>
        <input type='text' id='a_name' /> <br> 
        Age:<br>
        <input type='number' id='a_age' /> <br> 
        Locale:<br>
        <input type='text' id='a_locale' /> <br>
        <br>
        <button onclick='addPerson()'>Submit</button>
        <span id="a_message"></span>
    </div>
    <hr/>
    <div class='container'>
        <h3>Get Person</h3>
        Id:<br>
        <input type='number' id='g_id' /> <br><br>
        <button onclick='getPerson()'>Retrieve</button>
        <span id="g_message"></span>
        <br><br> 
        Name:<br>
        <span id='g_name'></span><br> 
        Age:<br>
        <span id='g_age'></span><br> 
        Locale:<br>
        <span id='g_locale'></span> <br> 
        <br> 
    </div>

    <hr/>

    <div class='container'>
        <h3>Delete Person</h3>
        Id:<input type='number' id='d_id'/> <br><br>
        <button onclick="deletePerson();">Delete</button>
        <span id="d_message"></span>
    </div>

    <script>
    
    function addPerson() {
        var name = $('#a_name').val();
        var age = $('#a_age').val();
        var locale = $('#a_locale').val();

        var URL = '/resources/data/';
        var dataObject = { 'name': name, 'age': age, 'locale': locale };
        $.ajax({
            url: URL,
            type: 'PUT',    
            data: JSON.stringify(dataObject),
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function(result) {
                $('#a_message').html("Added successfully as id " + result.id );
            },
            error: function(xhr, error){
                $('#d_message').html("Failed to delete " + xhr.status);
            }
        });
    }

    function getPerson() {
        var URL = '/resources/data/' + $('#g_id').val();
        $.get(URL, function(data, status){
            $('#g_name').html(data.name);
            $('#g_age').html(data.age);
            $('#g_locale').html(data.locale);
        }).done(function() {
            $('#g_message').html("Get Person Info successfully");
        })
        .fail(function() {
            $('#g_message').html("Failed to get ");
        })
    }

    function deletePerson() {
        var URL = '/resources/data/' + $('#g_id').val();
        $.ajax({
            url: URL,
            type: 'DELETE',
            success: function(result) {
                $('#d_message').html("Deleted successfully");
            },
            error: function(xhr, error){
                $('#d_message').html("Failed to delete " + xhr.status);
            }
        });
    }

    </script>
</body>
</html>
