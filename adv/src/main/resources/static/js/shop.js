function updateItemCount() {
    // Get the total number of products
    var totalProducts = document.querySelectorAll('.col-md-4.col-sm-6').length;
    
    // Update the total products count
    document.getElementById('total-products').textContent = totalProducts;
    
    // Update the displayed range
    var showSelect = document.getElementById('show-select');
    var startIndex = 1;
    var endIndex = Math.min(totalProducts, parseInt(showSelect.value));
    document.getElementById('item-count').textContent = "Items " + startIndex + "-" + endIndex + " of " + totalProducts;
}

// Call updateItemCount initially to set initial count
updateItemCount();

// Function to handle sorting
function sortBy(option) {
    // Get the container of products
    var productsContainer = document.querySelector('.containerr .row');

    // Get all product items
    var products = Array.from(productsContainer.children);

    // Sort products based on the selected option
    if (option === 'name') {
        products.sort((a, b) => {
            var nameA = a.querySelector('.title a').innerText.toUpperCase();
            var nameB = b.querySelector('.title a').innerText.toUpperCase();
            if (nameA < nameB) return -1;
            if (nameA > nameB) return 1;
            return 0;
        });
    } else if (option === 'price_asc') {
        products.sort((a, b) => {
            var priceA = parseFloat(a.querySelector('.price').innerText.split(' ')[1]);
            var priceB = parseFloat(b.querySelector('.price').innerText.split(' ')[1]);
            return priceA - priceB;
        });
    } else if (option === 'price_desc') {
        products.sort((a, b) => {
            var priceA = parseFloat(a.querySelector('.price').innerText.split(' ')[1]);
            var priceB = parseFloat(b.querySelector('.price').innerText.split(' ')[1]);
            return priceB - priceA;
        });
    }

    // Append sorted products to the container
    products.forEach(product => {
        productsContainer.appendChild(product);
    });
}

// Add event listener to the select element
document.getElementById('sort-by-select').addEventListener('change', function () {
    var selectedOption = this.value;
    sortBy(selectedOption);
});

// Function to handle pagination
function updatePagination(totalProducts, itemsPerPage) {
    var pagination = document.getElementById('pagination');
    pagination.innerHTML = ''; // Clear previous pagination links

    var totalPages = Math.ceil(totalProducts / itemsPerPage);

    for (var i = 1; i <= totalPages; i++) {
        var li = document.createElement('li');
        var a = document.createElement('a');
        a.href = '#';
        a.textContent = i;
        a.addEventListener('click', function (event) {
            event.preventDefault();
            var pageNum = parseInt(event.target.textContent);
            showPage(pageNum, itemsPerPage);
        });
        li.appendChild(a);
        pagination.appendChild(li);
    }
}

// Function to show products for the selected page
function showPage(pageNum, itemsPerPage) {
    var products = document.querySelectorAll('.col-md-4.col-sm-6');
    var startIndex = (pageNum - 1) * itemsPerPage;
    var endIndex = Math.min(startIndex + itemsPerPage, products.length);

    products.forEach(function (product, index) {
        if (index >= startIndex && index < endIndex) {
            product.style.display = 'block';
        } else {
            product.style.display = 'none';
        }
    });

    // Update the displayed range
    document.getElementById('pagination-text').textContent = "Items " + (startIndex + 1) + "-" + endIndex + " of " + products.length;
}

// Update the updateItemCount function to show initial products and pagination
function updateItemCount() {
    var totalProducts = document.querySelectorAll('.col-md-4.col-sm-6').length;
    var itemsPerPage = 12;
    showPage(1, itemsPerPage);
    updatePagination(totalProducts, itemsPerPage);
}

// Call updateItemCount initially to set initial count and pagination
updateItemCount();



