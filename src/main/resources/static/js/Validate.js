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
        if (document.getElementById("endDate").value < document.getElementById("startDate").value)
        {
            alert("End date must occur after Start date ");

            // Change the value back to the previous valid answer
            return false;
        }
        return true;
    }
