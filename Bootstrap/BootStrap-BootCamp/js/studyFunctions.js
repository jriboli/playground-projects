// ----------------------------------------------------
// LIKE/DECLINE buttons
// ----------------------------------------------------
// Example AJAX request for liking a study record
$(".like-button").on("click", function () {
  var studyRecordId = $(this).data("study-record-id");

  $.ajax({
    url: "/api/like/" + studyRecordId,
    method: "POST",
    success: function (response) {
      // Handle success, update UI as needed
    },
    error: function (error) {
      // Handle error
    },
  });
});

// Example AJAX request for removing a study record
$(".remove-button").on("click", function () {
  var studyRecordId = $(this).data("study-record-id");
  var recordContainer = $(this).closest(".study-record-container"); // Adjust this selector based on your HTML structure

  $.ajax({
    url: "/api/remove/" + studyRecordId,
    method: "DELETE",
    success: function (response) {
      // Remove the study record visually
      recordContainer.remove();
    },
    error: function (error) {
      // Handle error
    },
  });
});

// ----------------------------------------------------
// LOAD MORE button
// ----------------------------------------------------

var currentPage = 1; // Track the current page

// Function to fetch and append more study records
function loadMoreRecords() {
  $.ajax({
    url: "/api/study-records?page=" + currentPage,
    method: "GET",
    success: function (response) {
      if (response.length > 0) {
        // Append new records to the DOM
        appendRecordsToDOM(response);
        currentPage++;
      } else {
        // No more records, hide the "Load More" button or provide a message
        $("#loadMoreButton").hide();
      }
    },
    error: function (error) {
      // Handle error
    },
  });
}

// Event listener for the "Load More" button
$("#loadMoreButton").on("click", function () {
  loadMoreRecords();
});

// Initial load of the first set of records when the page loads
loadMoreRecords();

// Function to append records to the DOM
function appendRecordsToDOM(records) {
  // Implement this function based on your HTML structure
  // Append the new records to the existing container
}

// ----------------------------------------------------
// KEEP POSITION button
// ----------------------------------------------------

// Function to store the user's current position in session storage
function storeScrollPosition() {
  var scrollPosition = $(window).scrollTop();
  sessionStorage.setItem("scrollPosition", scrollPosition);
}

// Function to retrieve and scroll to the stored position
function scrollToStoredPosition() {
  var storedPosition = sessionStorage.getItem("scrollPosition");
  if (storedPosition !== null) {
    $(window).scrollTop(storedPosition);
  }
}

// Event listener for the "View Details" button
$(".view-details-button").on("click", function () {
  // Store the current scroll position before navigating
  storeScrollPosition();
  // Navigate to the detailed view page
  window.location.href = "/detailed-view";
});

// Initial scroll to the stored position when the page loads
scrollToStoredPosition();
