import boto3
from botocore.exceptions import NoCredentialsError

# Initialize S3 client
s3 = boto3.client('s3')

bucket_name = 'lg-data-report'
base_url = 'https://report-testing.burgershop.io/'  # Replace with your actual base URL

def generate_index_html():
    try:
        # List all objects in the root of the bucket and get subfolders only
        result = s3.list_objects_v2(Bucket=bucket_name, Delimiter='/')

        # Create the HTML structure with Bootstrap for styling
        html_content = """
        <html>
        <head>
            <title>All report</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        </head>
        <body>
            <div class="container mt-5">
                <h1 class="text-center">All report</h1>
                <ul class="list-group mt-4">
        """

        # Only list "folders" (prefixes) in the root directory
        if 'CommonPrefixes' in result:
            for prefix in result['CommonPrefixes']:
                folder_name = prefix['Prefix'].rstrip('/')  # Remove trailing slash
                # Construct the full link using the base_url and subfolder name
                folder_url = f'{base_url}{folder_name}/index.html'
                # Add each subfolder as a hyperlink to the index.html
                html_content += f'<li class="list-group-item"><a href="{folder_url}" target="_blank">{folder_name}</a></li>'

        html_content += """
                </ul>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        </body>
        </html>
        """

        # Save the index.html file
        with open("index.html", "w") as file:
            file.write(html_content)

        print("index.html generated successfully!")

    except NoCredentialsError:
        print("Credentials not available")

# Call the function to generate index.html
generate_index_html()
