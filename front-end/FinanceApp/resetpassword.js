document.getElementById('btnChangePassword').addEventListener('click',function(){

    const floatingEmail= document.getElementById('floatingEmail').value
    const floatingPasswordold=document.getElementById('floatingPasswordold').value
    const floatingPasswordnew=document.getElementById('floatingPasswordnew').value
    const selectObject = document.getElementById('role')
    //'options' returns an array of all options present in the SELECT element
    const allOptions = selectObject.options
    //get the index of the selected option
    const index = selectObject.selectedIndex
    //now get the selected option object from the SELECT element
    const selectedOption = allOptions[index]
    const role_id=selectedOption.value
    
    const data ={
        username:floatingEmail,
        password:floatingPasswordold,
        role:role_id
    }
    //console.log(data)
    const req = new XMLHttpRequest()
    req.onreadystatechange = function () {
        if (req.status === 200 && req.readyState === 4) {
            window.alert(req.responseText)
            window.open('/login.html','_self')
            
            
        }
        else if(req.status===400 && req.readyState===4){
            window.alert("No Users Found! Please try again with other information")
            
        }
       
    }
    req.open('POST', `http://localhost:8080/FinanceProjectAppDay2/rest/mail/reset/password?newpassword=${floatingPasswordnew}`, true)
    req.setRequestHeader("Content-Type", "application/json")

    //localStorage.setItem()
    req.send(JSON.stringify(data));

})





