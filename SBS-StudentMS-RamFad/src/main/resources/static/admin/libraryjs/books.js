
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
																				//VIEW BOOK MODAL

console.log("This is books js page.");

/*
 $('document').ready(function()
{
	 //$('table #editButton').on('click',function(event)
	   $(document).on('click', 'table .editButton', function(event)
			   {
			        event.preventDefault();
			        var href= $(this).attr('href');
			        $.get(href, function(bookData,status)
						{
			       			$('#id_V').val(bookData.id);
			        		$('#isbn_V').val(bookData.isbn);
			        		$('#name_V').val(bookData.name);
			       			$('#description_V').val(bookData.description);
			        	}
					);
			        $('#viewBookModal').modal('show');
			    }); 
});
*/
/*
$(document).ready(function() {
   
    $(document).on('click', 'table .viewButton', function(event) {
        event.preventDefault();
        var href = $(this).attr('href'); 

        $.get(href, function(bookData, status) { // AJAX GET request to fetch book details
            // Assuming the server responds with JSON data that includes book attributes
            $('#id_V').val(bookData.id);
            $('#isbn_V').val(bookData.isbn);
            $('#name_V').val(bookData.name);
            $('#description_V').val(bookData.description);
  	        // $('#authors_V').val(bookData.authors);
            // $('#categories_V').val(bookData.categories);
            // $('#publishers_V').val(bookData.publishers);
            // $('#viewBookModal').modal('show'); // Use 'show' to explicitly open the modal
        }).fail(function(jqXHR, textStatus, errorThrown) { // Error handling
            console.error('Failed to fetch details: ' + textStatus + ' ' + errorThrown);
        });
    });
});
*/

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
