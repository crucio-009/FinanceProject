const btnElement=document.getElementById('btnSubmit')
btnElement.addEventListener('click',function(){

        // const appnoElement = document.getElementById('inputAppNo')
        //const idElement = document.getElementById('inputId')
        //console.log(idElement)
        //const loantypeElement = document.getElementById('inputLoantype')
        
        const amtElement = document.getElementById('inputAmt')
        const tenureElement = document.getElementById('inputTenure')
        const img1Element = document.getElementById('inputFile')

        const selectObject = document.getElementById('inputType')
        //'options' returns an array of all options present in the SELECT element
        const allOptions = selectObject.options
        //get the index of the selected option
        const index = selectObject.selectedIndex
        //now get the selected option object from the SELECT element
        const selectedOption = allOptions[index]


        const file = img1Element.files[0];
        const reader = new FileReader();
        
        
        reader.addEventListener(
            'load',
            function () {
                
                const data = {
                    // applicationId:appnoElement.value,
                    customerId:localStorage.getItem('custid'),
                    loanId:selectedOption.value,
                    loanAmount:amtElement.value,
                    tenure:tenureElement.value,
                    interestRate:5,
                    //applicationDate:'2023-08-18',
                    applicationStatus:'PENDING',
                    documentTwo:"null",
                    remarks:null,
                    documentOne:reader.result
                }


                const req = new XMLHttpRequest()
                    req.onreadystatechange = function () {
                        if (req.status === 200 && req.readyState === 4) {
                            window.alert(req.responseText)
                        }
                    }
                req.open('POST', 'http://localhost:8080/FinanceProjectAppDay2/rest/loan/applications/add', true)
                req.setRequestHeader("Content-Type", "application/json")
                req.send(JSON.stringify(data))

    })
    reader.readAsDataURL(file);
})

//function to load data in SELECT element
function loadLoansInSelect(loans) {
    //console.log(loans)
    const selectElement = document.getElementById('inputType')
    loans.forEach(
            (p) => {
                const option = document.createElement('option')
                option.text = p.loanType
                option.value = p.loanId
                selectElement.options.add(option)
            }
        )
}

function getLoanTypes(){

    var req = new XMLHttpRequest()
    req.onreadystatechange = function () {
        if (req.status === 200 && req.readyState === 4) {
            //console.log(req.responseText)
            const resp=JSON.parse(req.responseText)
            loadLoansInSelect(resp.responseData)
        }
    }
    req.open('GET','http://localhost:8080/FinanceProjectAppDay2/rest/loans/all',true)
    req.setRequestHeader('Content-Type', 'application/json')
    req.send()

}


window.addEventListener('DOMContentLoaded',
    function(){
        getLoanTypes()
        
    }
)


