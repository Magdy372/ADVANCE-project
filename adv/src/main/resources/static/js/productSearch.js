$(document).ready(function() {
    $('#searchInput').on('input', function() {
        var query = $(this).val();
        if (query.length >= 2) {
            $.ajax({
                url: '/products/search?name=' + query,
                type: 'GET',
                success: function(data) {
                    if (data.length > 0) {
                        displayResults(data);
                    } else {
                        $('#searchResults').empty();
                        $('#searchResults').append('<li>No products found</li>');
                    }
                }
            });
        } else {
            $('#searchResults').empty(); // Clear the search results if input is empty
        }
    });
    
    function displayResults(products) {
        var resultList = $('#searchResults');
        resultList.empty();
        products.forEach(function(product) {
            var listItem = $('<li><img src="/uploads/' + product.id + '/' + product.photos + '" alt="' + product.name + '"> ' + product.name + '</li>');
            listItem.click(function() {
                // Open product details in a new page
                window.open('products/product-details/' + product.id, '_blank');
            });
            resultList.append(listItem);
        });
    }
});