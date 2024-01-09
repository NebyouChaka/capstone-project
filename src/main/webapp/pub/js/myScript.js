    function addIngredientInput() {
        var container = document.getElementById("ingredientsContainer");
        var ingredientDiv = document.createElement("div");
        ingredientDiv.className = "ingredient-group row mb-3";

        ingredientDiv.innerHTML = `
            <div class="col">
                <label style="color: white;">Ingredient Name:</label>
                <input type="text" name="ingredientName" class="form-control" required>
            </div>
            <div class="col">
                <label style="color: white;">Measurement:</label>
                <input type="text" name="measurement" class="form-control"  required>
            </div>
            <div class="col">
                <label style="color: white;">Quantity:</label>
                <input type="number" name="quantity" class="form-control" required>
            </div>
            <div class="col-auto">
                <button type="button" class="btn btn-danger" onclick="removeIngredientInput(this)">Remove</button>
            </div>
        `;
        container.appendChild(ingredientDiv);
    }

    function removeIngredientInput(button) {
        button.parentNode.remove();
    }
document.addEventListener("DOMContentLoaded", function () {
    const addIngredientButton = document.getElementById("addIngredientButton");
    addIngredientButton.addEventListener("click", addIngredientInput);
});


    // Call addIngredientInput when the window loads
    window.onload = function() {
        addIngredientInput();
    };

