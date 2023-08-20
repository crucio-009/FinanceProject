function setCustomerID(email){
    const userId=email;
    //console.log(userId);
    const req = new XMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.status === 200 && req.readyState === 4) {
            const resp2=JSON.parse(req.responseText);
            //console.log(resp2)
            localStorage.setItem('custid',resp2.responseData.customerId)
        }
    }
    req.open('GET',`http://localhost:8080/FinanceProjectAppDay2/rest/customers/get?email=${userId}`,true)
    req.setRequestHeader('Content-Type', 'application/json')
    req.send()
}
document.getElementById('btnLogin').addEventListener('click',function(){

    const floatingEmail= document.getElementById('floatingEmail').value
    const floatingPassword=document.getElementById('floatingPassword').value
    const selectObject = document.getElementById('role')
    //'options' returns an array of all options present in the SELECT element
    const allOptions = selectObject.options
    //get the index of the selected option
    const index = selectObject.selectedIndex
    //now get the selected option object from the SELECT element
    const selectedOption = allOptions[index]
    const role_id=selectedOption.value


    // const data = new URLSearchParams();
    // data.append('email', floatingEmail);
    // data.append('password', floatingPassword);
    // data.append('roleid',role_id);
    
    //const data="username=floatingEmail&password=floatingPassword&roleid=role_id"
    const data = `username=${encodeURIComponent(floatingEmail)}&password=${encodeURIComponent(floatingPassword)}&roleid=${encodeURIComponent(role_id)}`;
    //console.log(data)
    const req = new XMLHttpRequest()
    req.onreadystatechange = function () {
        if (req.status === 200 && req.readyState === 4) {
            const token=req.responseText
            localStorage.setItem('token', token);
            localStorage.setItem('userid',floatingEmail)
            if(role_id==0){
                setCustomerID(floatingEmail);
                window.alert("Login Success")
                window.open("customer/addloanapplication.html",'_self')
                
            }
            else  if(role_id==1){
                window.alert("Login Success")
                window.open("clerk/addcustomer.html",'_self')
            }
            else  if(role_id==2){
                window.alert("Login Success")
                window.open("manager/loanapplicationdetails.html",'_self')
            }
        }
        else if(req.status===403 && req.readyState===4){
            window.alert("Invalid credentials")
        }
       
    }
    req.open('POST', 'http://localhost:8080/FinanceProjectAppDay2/rest/authenticate', true)
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")

    //localStorage.setItem()
    req.send(data);

})



