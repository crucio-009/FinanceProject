//AJAX => asynchronous JavaScript and XML

//function to load data in Table body element
function loadCustomerDetails(customers) {
    const tblBody = document.getElementById('tblBody')
    customers
        .forEach(
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
            //part-1: link change event of the SELECT element with a function
            //this function will be called back when the change event of the SELECT element is fired by changing the selection from SELECT element
            // document
            //     .getElementById('ddlProducts')
            //     .addEventListener('change', getProductDataById)

            //fetch all the products from RESTful API server and load the product names and ids in the SELECT element
            getCustomers()
        }
    )
