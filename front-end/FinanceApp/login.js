// document.getElementById("role").addEventListener('change', function(){
//     localStorage.setItem('role_id', this.value);
//     sessionStorage.setItem('role_id', this.value);
//     document.querySelector("body > div.container-fluid.d-flex.align-items-center.py-4.hero > main > div > span > a").href=""
// });

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
    console.log(data)
    const req = new XMLHttpRequest()
    req.onreadystatechange = function () {
        if (req.status === 200 && req.readyState === 4) {
            const token=req.responseText
            localStorage.setItem('token', token);
            window.alert("Login Success")
            if(role_id==0){
                window.open("customer/addloanapplication.html",'_self')
            }
            else  if(role_id==1){
                window.open("clerk/addcustomer.html",'_self')
            }
            else  if(role_id==2){
                window.open("manager/managerhome.html",'_self')
            }
            // const serviceResponseObject = JSON.parse(req.responseText)
            // loadCustomerDetails(serviceResponseObject.responseData)
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

















// document.getElementsByClassName('signup-link')[0].addEventListener('click', function(){
//     if(localStorage.getItem('role_id') == 0){
//         this.href="usersignup.html";
//     }
//     else if(localStorage.getItem('role_id') == 2){
//         this.href="managersignup.html";
//     }
//     else if(localStorage.getItem('role_id') == 1){
//         this.href="clerksignup.html";
//     }
//     else{
//         this.href="login.html"
//     }
// });

// document.getElementById("btnLogin").addEventListener('click',function(){
    
//     window.open('./usersignup.html','_self');
// });