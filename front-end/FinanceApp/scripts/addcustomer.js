const btnElement=document.getElementById('btnSubmit')
btnElement.addEventListener('click',function(){

    // const idElement = document.getElementById('inputId')
    const nameElement = document.getElementById('inputName')
    const emailElement = document.getElementById('inputEmail')
    const genderElement = document.getElementById('inputGender')
    const phoneElement = document.getElementById('inputPhone')

    const data={
        // customerId:idElement.value,
        customerName:nameElement.value,
        gender:genderElement.value,
        phoneNo:phoneElement.value,
        emailId:emailElement.value
    };
    //console.log(data);

    sendAsyncRequest('POST',
    'http://localhost:8080/FinanceProjectAppDay2/rest/customers/add',
    function(resp){
        const response=JSON.parse(resp);
        
        if(response.responseData==true){
            window.alert("Customer Added!");
            window.open('customerdetails.html','_self')
        }
        else{
            window.alert(resp);
        }
        
        
        

    },
    'application/json',
    data
    );

    // const req=new XMLHttpRequest();
    // req.onreadystatechange = () => {
    //     if (req.status === 200 && req.readyState === 4) {
    //         window.alert(req.responseText)
    //     }
    // }
    
    // req.open('POST', 'http://localhost:8080/FinanceProjectAppDay2/rest/customers/add',true);
    // req.setRequestHeader("Content-Type","application/json")
    // req.send(JSON.stringify(data));


});
