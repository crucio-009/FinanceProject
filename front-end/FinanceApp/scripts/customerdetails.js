//AJAX => asynchronous JavaScript and XML

document.getElementById('btnSearch').addEventListener('click',function(){
    const searchEle=document.getElementById('inputsearch')
    //console.log(searchEle.value)
    if(searchEle.value=='') {
        getCustomers();
        return;
    }

        const req = new XMLHttpRequest()
        req.onreadystatechange = () => {
            if (req.status === 200 && req.readyState === 4) {
                //console.log(req.responseText)
                const serviceResponseObject = JSON.parse(req.responseText)
                //console.log(serviceResponseObject)
                loadCustomerDetails(serviceResponseObject.responseData)
            }
        }

        req.open('GET', `http://localhost:8080/FinanceProjectAppDay2/rest/customers/get/${searchEle.value}`);
        req.send()

})

//function to load data in Table body element
function loadCustomerDetails(customers) {
    const tblBody = document.getElementById('tblBody');
    
    while(tblBody.firstChild){
        tblBody.removeChild(tblBody.firstChild)
    }
    if(customers.length>1){
        customers.forEach(
                (c) => {
                    const row=document.createElement("tr")
                    const id=document.createElement("td")
                    const name=document.createElement("td")
                    const gender=document.createElement("td")
                    const phoneno=document.createElement("td")
                    const email=document.createElement("td")
                    id.textContent=c.customerId;
                    name.textContent=c.customerName;
                    gender.textContent=c.gender;
                    phoneno.textContent=c.phoneNo;
                    email.textContent=c.emailId;
                    row.appendChild(id)
                    row.appendChild(name)
                    row.appendChild(gender)
                    row.appendChild(phoneno)
                    row.appendChild(email)
                    tblBody.appendChild(row)

                }
            )
    }
    else{
        const row=document.createElement("tr")
        const id=document.createElement("td")
        const name=document.createElement("td")
        const gender=document.createElement("td")
        const phoneno=document.createElement("td")
        const email=document.createElement("td")
        id.textContent=customers.customerId;
        name.textContent=customers.customerName;
        gender.textContent=customers.gender;
        phoneno.textContent=customers.phoneNo;
        email.textContent=customers.emailId;
        row.appendChild(id)
        row.appendChild(name)
        row.appendChild(gender)
        row.appendChild(phoneno)
        row.appendChild(email)
        tblBody.appendChild(row)
    }
}



//function to get all the customers
function getCustomers() {
    const req = new XMLHttpRequest()

    req.onreadystatechange = () => {
        if (req.status === 200 && req.readyState === 4) {
            //console.log(req.responseText)
            const serviceResponseObject = JSON.parse(req.responseText)
            //console.log(serviceResponseObject)
            loadCustomerDetails(serviceResponseObject.responseData)
        }
    }

    req.open('GET', 'http://localhost:8080/FinanceProjectAppDay2/rest/customers/all')
    req.send()
}


//code which will be executed immediately afetr DOM content creation is completed and the page is loaded in the browser
window
    .addEventListener(
        'DOMContentLoaded',
        function () {
           getCustomers()
        }
    )
