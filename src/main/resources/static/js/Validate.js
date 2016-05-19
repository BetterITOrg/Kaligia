<!-- Validation for empty data fields -->
<!-- Author: huzefasg@gmail.com -->
function validate()
    { 
        if (document.getElementById("LoginId").value == "")
        {
        	alert("LoginId cannot be empty ");
        	return false;
        }
        if (document.getElementById("Password").value == "")
    	{
        	alert("Password cannot be empty ");
        	return false;
    	}
        if (document.getElementById("FirstName").value == "")
    	{
    	alert("FirstName cannot be empty ");
    	return false;
    	}
        if (document.getElementById("LastName").value == "")
    	{
    	alert("LastName cannot be empty ");
    	return false;
    	}
        if (document.getElementById("Email").value == "")
    	{
    	alert("Email cannot be empty ");
    	return false;
    	}
        if (document.getElementById("Phone").value == "")
    	{
    	alert("Phone cannot be empty ");
    	return false;
    	}
        if (document.getElementById("startDate").value == "")
    	{
    	alert("Please select a Start Date");
    	return false;
    	}
        if (document.getElementById("endDate").value == "")
    	{
    	alert("Please select an End Date");
    	return false;
    	}
        
        var today = new Date();
        var day = today.getDate();
        var month = today.getMonth()+1;
        var year = today.getFullYear();
        var datestring = year + "-" + ("00"+month).slice(-2) + "-" + ("00"+day).slice(-2);
        today = new Date(datestring);
        var sDay  = new Date(document.getElementById("startDate").value);
        var eDay  = new Date(document.getElementById("endDate"  ).value);
        if( sDay < today )
        {
            alert("Start date cannot be before Today.");

            return false;
        }
        
        if ( sDay >= eDay )
        {
            alert("End Date has to be after start date.");

            return false;
        }
        if ( eDay < today )
        {
            alert("End date must occur after today ");

            return false;
        }
        return true;
    }
