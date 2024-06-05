
export function toastManager(message, color) 
{
  const toastContainer = document.getElementById('toastContainer');
  if (!toastContainer) return; // Exit if no toast container is found


	console.log("Message Value = " ,message, " Color value = " ,color)
  // Assign a default color if not specified
  if (!color) {
    color = 'bg-success';//bg-warning ,bg-success,bg-info,bg-danger
  }

  // Ensure color class is applied correctly
  const colorClass = `text-white ${color}`;

  // Clear previous toasts
  toastContainer.innerHTML = '';

  // Create the toast element dynamically
  const toastHTML = `
    <div class="toast align-items-center ${colorClass} border-0" role="alert" aria-live="assertive" aria-atomic="true" data-bs-delay="7000">
      <div class="d-flex">
        <div class="toast-body">${message}</div>
        <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
      </div>
    </div>
  `;

  // Add the toast HTML to the container
  toastContainer.innerHTML = toastHTML;

  // Initialize and show the toast
  const toastEl = toastContainer.querySelector('.toast');
  const toast = new bootstrap.Toast(toastEl);
  toast.show();
}