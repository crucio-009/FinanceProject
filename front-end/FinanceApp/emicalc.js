var chartInstances = {}; // To store chart instances for different tabs

// Draw Pie Chart
function calculateLoan(type, principal, totalInterest) {
    // Get loan type specific inputs and calculate EMI
    // Calculate pie chart data
    var interestPaid = totalInterest;

    var pieChartCan = document.getElementById('homePieChart')
    pieChartCan.width = 50;    
    pieChartCan.height = 50;
    var pieChartCanvas = pieChartCan.getContext('2d')

    var existingChart = chartInstances[type];

    if (existingChart) {
        existingChart.destroy(); // Destroy existing chart instance
    }

    var pieChartData = {
        labels: ['Principal Paid', 'Interest Paid'],
        datasets: [{
            data: [principal, interestPaid],
            backgroundColor: ['#007bff', '#dc3545'],
        }]
    };

    var pieChartConfig = {
        type: 'pie',
        data: pieChartData
    };

    chartInstances[type] = new Chart(pieChartCanvas, pieChartConfig);
}

// Calculate EMI
function calculateEmi(principal, interestRate, loanTenure){
    var monthlyInterestRate = (interestRate / 100) / 12;
    var emi = (principal * monthlyInterestRate) * (Math.pow(1 + monthlyInterestRate, loanTenure)) / (Math.pow(1 + monthlyInterestRate, loanTenure) - 1);
    return emi;
}

// Home Loan Calculator
var homePrincipalInput = document.getElementById('homePrincipal');
var homeInterestRateInput = document.getElementById('homeInterestRate');
var homeLoanTenureInput = document.getElementById('homeLoanTenure');
var homeCalculateBtn = document.getElementById('homeCalculateBtn');

homeCalculateBtn.addEventListener('click', function () {
    var principal = parseFloat(homePrincipalInput.value);
    var interestRate = parseFloat(homeInterestRateInput.value);
    var loanTenure = parseFloat(homeLoanTenureInput.value);

    var emi = calculateEmi(principal, interestRate, loanTenure);
    var totalInterest = emi * loanTenure - principal;

    var homeEmiResultDiv = document.getElementById("homeEmiResult");
    homeEmiResultDiv.innerHTML = `<p><strong>EMI:</strong> ₹${emi.toFixed(2)}</p>`;

    calculateLoan('home', principal, totalInterest);
});

// Display Slider Output
var homePrincipalOutput = document.getElementById('homePrincipalOutput');
var homeInterestOutput = document.getElementById('homeInterestOutput');
var homeTenureOutput = document.getElementById('homeTenureOutput');
homePrincipalOutput.innerHTML = homePrincipalInput.value;
homeTenureOutput.innerHTML = homeLoanTenureInput.value;
homeInterestOutput.innerHTML = homeInterestRateInput.value;

var emi = calculateEmi(homePrincipalInput.value, homeInterestRateInput.value, homeLoanTenureInput.value);
var totalInterest = emi *  homeLoanTenureInput.value - homePrincipalInput.value;

var homeEmiResultDiv = document.getElementById("homeEmiResult");
homeEmiResultDiv.innerHTML = `<p><strong>EMI:</strong> ₹${emi.toFixed(2)}</p>`;

calculateLoan('home', homePrincipalInput.value, totalInterest);

homePrincipalInput.oninput = function() {
    homePrincipalOutput.innerHTML = this.value;
}

homeInterestRateInput.oninput = function() {
    homeInterestOutput.innerHTML = this.value;
}

homeLoanTenureInput.oninput = function() {
    homeTenureOutput.innerHTML = this.value;
}
