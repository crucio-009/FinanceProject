function getLoanApplications(){
    
    const req = new XMLHttpRequest()
    req.onreadystatechange = function () {
        if (req.status === 200 && req.readyState === 4) {
            console.log(req.responseText)
            const jsData = JSON.parse(req.responseText)
            const tableBody = document.getElementById('tblBody')
            tableBody.innerHTML = ""
            console.log(jsData)

            for (const data of jsData.responseData) {

                const tdId = document.createElement('td')
                tdId.innerText = data.applicationId.toString()

                const tdcusId = document.createElement('td')
                tdcusId.innerText = data.customerId.toString()

                const tdloanId = document.createElement('td')
                tdloanId.innerText = data.loanId.toString()
                
                const tdloanAmount = document.createElement('td')
                tdloanAmount.innerText = data.loanAmount.toString()

                const tdinterestRate = document.createElement('td')
                tdinterestRate.innerText = data.interestRate.toString()

                const tdtenure = document.createElement('td')
                tdtenure.innerText = data.tenure.toString()

                const tdapplicationDate = document.createElement('td')
                tdapplicationDate.innerText = data.applicationDate.toString().slice(0,-1)
                //tdapplicationDate.innerText = data.applicationDate.toString()

                const tdapplicationStatus = document.createElement('td')
                tdapplicationStatus.innerText = data.applicationStatus.toString()

                const tdremarks= document.createElement('td')
                tdremarks.innerText = (data.remarks==undefined)?"-":data.remarks.toString()

                // const tdcusId = document.createElement('td')
                // tdcusId.innerText = data.applicationDate.toString()

                const imgEle = document.createElement('img')
                imgEle.src = data.documentOne
                imgEle.alt = "NA"
                imgEle.style.width = "200px";
                imgEle.style.margin = "2px";

                const tdImg = document.createElement('td')
                tdImg.appendChild(imgEle)

                const row = document.createElement('tr')
                row.append(tdId,tdcusId,tdloanId,tdloanAmount,tdinterestRate,tdtenure,tdapplicationDate,tdapplicationStatus,tdremarks,tdImg)

                tableBody.append(row)
            }
        }
    }
    req.open('GET', 'http://localhost:8080/FinanceProjectAppDay2/rest/loan/applications/all')
    req.send() 
}


window
    .addEventListener(
        'DOMContentLoaded',
        function () {
             getLoanApplications()
        }
    )
