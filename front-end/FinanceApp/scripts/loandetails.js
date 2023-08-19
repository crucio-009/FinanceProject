//AJAX => asynchronous JavaScript and XML

//function to load data in Table body element
function loadLoanDetails(loans) {
    const tblBody = document.getElementById('tblBody')
    loans.forEach(
            (c) => {
                const row=document.createElement("tr")
                const loan_id=document.createElement("td")
                const loan_type=document.createElement("td")
                const description=document.createElement("td")
                const interestrate=document.createElement("td")
                loan_id.textContent=c.loanId;
                loan_type.textContent=c.loanType;
                description.textContent=c.description;
                interestrate.textContent=c.interest_rate;
                row.appendChild(loan_id)
                row.appendChild(loan_type)
                row.appendChild(description)
                row.appendChild(interestrate)
                tblBody.appendChild(row)

            }
        )
}

//function to load data in form element
function displayData(productObject) {
    document.getElementById('spanProduct').innerText = productObject.productName;
    document.getElementById('txtName').value = productObject.productName;
    document.getElementById('txtPrice').value = productObject.price.toString();
    document.getElementById('txtDesc').value = productObject.description;
    document.getElementById('txtId').value = productObject.productId.toString();
    document.getElementById('txtCatId').value = productObject.categoryId.toString();
}

//function to get producy detail by a given id
function getCustomersDataById() {
    const req = new XMLHttpRequest()
    //console.log("readystate value(initial): " + req.readyState)

    req.onreadystatechange = () => {
        //console.log("readystate value: " + req.readyState)
        if (req.status === 200 && req.readyState === 4) {
            console.log(req.responseText)
            const serviceResponse = JSON.parse(req.responseText)
            console.log(serviceResponse)
            displayData(serviceResponse.responseData)
        }
    }

    const selectObject = document.getElementById('ddlProducts')
    //'options' returns an array of all options present in the SELECT element
    const allOptions = selectObject.options
    //get the index of the selected option
    const index = selectObject.selectedIndex
    //now get the selected option object from the SELECT element
    const selectedOption = allOptions[index]

    // console.log(selectedOption.text)
    // console.log(selectObject.value)

    req.open('GET', 'http://localhost:8080/FinanceProjectAppV1_MK/rest/loans/get/' + selectedOption.value)
    req.send()
}

//function to get all the products
function getLoanDetails() {
    const req = new XMLHttpRequest()

    req.onreadystatechange = () => {
        if (req.status === 200 && req.readyState === 4) {
            //console.log(req.responseText)
            const serviceResponseObject = JSON.parse(req.responseText)
            //console.log(serviceResponseObject)
            loadLoanDetails(serviceResponseObject.responseData)
        }
    }

    req.open('GET', 'http://localhost:8080/FinanceProjectAppDay2/rest/loans/all')
    req.send()
}

window.addEventListener(
        'DOMContentLoaded',
        function () {
           getLoanDetails()
        }
    )
