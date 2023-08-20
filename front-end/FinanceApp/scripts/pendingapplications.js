function getLoanApplications(){
    
    const req = new XMLHttpRequest()
    req.onreadystatechange = function () {
        if (req.status === 200 && req.readyState === 4) {
            //console.log(req.responseText)
            const jsData = JSON.parse(req.responseText)
            const tableBody = document.getElementById('tblBody')
            tableBody.innerHTML = ""
            //console.log(jsData)


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

                    const tdStatusElement = document.createElement("td");
                    // Create the "Approve" button
                    const approveButton = document.createElement("button");
                    approveButton.innerText = "Approve";
                    approveButton.classList.add('btn','btn-success')
                    approveButton.classList.add('btn','me-1')
                    approveButton.addEventListener("click", () => {
                        
                        const req=new XMLHttpRequest();

                        req.onreadystatechange = () =>{
                        if (req.status === 200 && req.readyState === 4) {

                            window.alert("Loan Application Approved!");
                            //sendEmail(localStorage.getItem('customeremailid','Loan Application Approved','Congratulations!!, Your Loan Application has been approved.')
                            location.reload();          

                        }
                    }
                        
                        req.open('POST', `http://localhost:8080/FinanceProjectAppDay2/rest/loan/applications/updatestatus/${tdId.innerText}?status=approved&customerid=${tdcusId.innerText}`);
                        req.setRequestHeader("Content-Type","application/json")
                        req.send()
                    
                    });

                    // Create the "Reject" button
                    const rejectButton = document.createElement("button");
                    rejectButton.innerText = "Reject";
                    rejectButton.classList.add('btn','btn-danger')
                    //rejectButton.classList.add('btn','m-2')
                    rejectButton.addEventListener("click", () => {
                        const req=new XMLHttpRequest();
                        req.onreadystatechange = ()=>{
                        if (req.status === 200 && req.readyState === 4) {
                            window.alert("Loan Application Rejected!");
                            location.reload();                            
                        }
                    }
                        req.open('POST', `http://localhost:8080/FinanceProjectAppDay2/rest/loan/applications/updatestatus/${tdId.innerText}?status=rejected&customerid=${tdcusId.innerText}`);
                        req.send()
                    });

                    // Append the buttons to the td element
                    tdStatusElement.appendChild(approveButton);
                    tdStatusElement.appendChild(rejectButton);

                    //tdapplicationDate.innerText = data.applicationDate.toString()

                    // const tdapplicationStatus = document.createElement('td')
                    // tdapplicationStatus.innerText = data.applicationStatus.toString()

                    // const tdremarks= document.createElement('td')
                    // tdremarks.innerText = (data.remarks==undefined)?"-":data.remarks.toString()

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
                    row.append(tdId,tdcusId,tdloanId,tdloanAmount,tdinterestRate,tdtenure,tdapplicationDate,tdImg,tdStatusElement)

                    tableBody.append(row)
                }
        }
    }
    req.open('GET', 'http://localhost:8080/FinanceProjectAppDay2/rest/loan/applications/get/pending')
    const token=localStorage.getItem('token')
    req.setRequestHeader('Authorization', `Bearer ${token}`)
    req.send()
}


window
    .addEventListener(
        'DOMContentLoaded',
        function () {
            getLoanApplications();
        }
    )
