import pandas as pd
from sklearn.cluster import KMeans
from sklearn.preprocessing import StandardScaler, OneHotEncoder
from sklearn.decomposition import PCA
import matplotlib.pyplot as plt
from sklearn import metrics

# Load the dataset
data = pd.read_excel('BankChurnersTrain.xls', engine='xlrd')

# Pre-processing
# Handle missing values, data cleaning, encode categorical variables, feature scaling

# Perform clustering
features = ['Age', 'CreditScore', 'Education_Level', 'Income_Category']
X = data[features]

# Encode categorical variables
categorical_features = ['Education_Level', 'Income_Category']
X_encoded = pd.get_dummies(X, columns=categorical_features)

# Scale the features
scaler = StandardScaler()
X_scaled = scaler.fit_transform(X_encoded)

# Apply K-means clustering
k = 3  # Number of clusters
kmeans = KMeans(n_clusters=k, n_init=10, random_state=42)
clusters = kmeans.fit_predict(X_scaled)

# Add the cluster labels to the dataset
data['Cluster'] = clusters

# Dimensionality reduction using PCA
pca = PCA(n_components=2)
pca_result = pca.fit_transform(X_scaled)
data['PC1'] = pca_result[:, 0]
data['PC2'] = pca_result[:, 1]

# Evaluate and visualize the results
# Calculate silhouette score, within-cluster sum of squares
silhouette_score = metrics.silhouette_score(X_scaled, clusters)
within_cluster_sum_of_squares = kmeans.inertia_

print("Silhouette Score:", silhouette_score)
print("Within-Cluster Sum of Squares:", within_cluster_sum_of_squares)

# Create scatter plot to visualize the clusters
plt.scatter(pca_result[:, 0], pca_result[:, 1], c=clusters, cmap='viridis')
plt.xlabel('PC1')
plt.ylabel('PC2')
plt.title('Clustering Results')
plt.show()

# Print the profiles of high-churn customers
high_churn_cluster = 2  # Update with the cluster associated with high-churn customers
high_churn_customers = data[data['Cluster'] == high_churn_cluster]
print(high_churn_customers)
