const btnElement=document.getElementById('btnSubmit')
btnElement.addEventListener('click',function(){

    const appnoElement = document.getElementById('inputAppNo')
    const idElement = document.getElementById('inputId')
    const loantypeElement = document.getElementById('inputLoantype')
    const amtElement = document.getElementById('inputAmt')
    const tenureElement = document.getElementById('inputTenure')

    const img1Element = document.getElementById('inputFile')
    const file = img1Element.files[0];
    const reader = new FileReader();
    
    reader.addEventListener(
        'load',
        function () {
            
            const data = {
                applicationId:appnoElement.value,
                customerId:idElement.value,
                loanId:loantypeElement.value,
                loanAmount:amtElement.value,
                tenure:tenureElement.value,
                interestRate:5,
                applicationDate:'2023-08-18',
                applicationStatus:'pending',
                documentTwo:"null",
                remarks:null,
                documentOne:reader.result
            }

            // sendAsyncRequest('POST',
            // 'http://localhost:8080/FinanceProjectAppDay2/rest/loan/applications/add',
            // function(resp){
            //    window.alert(resp)
            // },
            // 'application/json',
            // data
            // )

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
